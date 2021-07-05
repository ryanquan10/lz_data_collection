(function () {
  // 在当前窗口作用域下，储存父窗口window对象
  window.win = self !== top ? window.parent : window;
  /**
   * HTTP 请求处理
   */
  var http = Vue.prototype.$http = axios.create({
    baseURL: window.SITE_CONFIG['apiURL'],
    timeout: 1000 * 180,
    withCredentials: true
  });
  // 请求拦截
  http.interceptors.request.use(function (config) {
    config.headers['Accept-Language'] = Cookies.get('language') || 'zh-CN';
    // 默认参数
    var defaults = {};
    // 防止缓存，GET请求默认带_t参数
    if (config.method === 'get') {
      config.params = _.merge({}, config.params, { '_t': new Date().getTime() });
    }
    if (_.isPlainObject(config.params)) {
      config.params = _.merge({}, defaults, config.params);
    }
    if (_.isPlainObject(config.data)) {
      config.data = _.merge({}, defaults, config.data);
      if (/^application\/x-www-form-urlencoded/.test(config.headers['content-type'])) {
        config.data = Qs.stringify(config.data);
      }
    }
    return config;
  }, function (error) {
    return Promise.reject(error);
  });
  // 响应拦截
  http.interceptors.response.use(function (response) {
    if (response.data.code === 401) {
      win.location.href = 'login.html';
      return Promise.reject(response.data.msg);
    }
    return response;
  }, function (error) {
    console.error(error);
    return Promise.reject(error);
  });

  /**
   * 权限
   */
  Vue.prototype.$hasPermission = function (key) {
    return win.SITE_CONFIG['permissions'].indexOf(key) !== -1 || false;
  };

  /**
   * 工具类
   */
  Vue.prototype.$utils = {
    // 获取svg图标(id)列表
    getIconList: function () {
      var res = [];
      document.querySelectorAll('svg symbol').forEach(function (item) {
        res.push(item.id);
      });
      return res;
    },
    // 获取url地址栏参数
    getRequestParams: function () {
      var str  = win.location.search || win.location.hash.indexOf('?') >= 1 ? win.location.hash.replace(/.*(\?.*)/, '$1') : '';
      var args = {};
      if (!/\^?(=+)/.test(str)) {
        return args;
      }
      var pairs = str.substring(1).split('&');
      var pos   = null;
      for(var i = 0; i < pairs.length; i++) {
        pos = pairs[i].split('=');
        if(pos == -1) {
          continue;
        }
        args[pos[0]] = pos[1];
      }
      return args;
    }
  };

  /**
   * 验证
   */
  Vue.prototype.$validate = {
    // 邮箱
    isEmail: function (s) {
      return /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(s);
    },
    // 手机号码
    isMobile: function (s) {
      return /^1[0-9]{10}$/.test(s);
    },
    // 电话号码
    isPhone: function (s) { 
      return /^([0-9]{3,4}-)?[0-9]{7,8}$/.test(s);
    },
    // URL地址
    isURL: function (s) { 
      return /^http[s]?:\/\/.*/.test(s);
    },
    // 数字
    ifNum: function (s) { 
      //return /^[A-Za-z0-9]+$/.test(s);
      return /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/.test(s);
    }
  };

  /**
   * main-sidebar-submenu组件
   */
  (function () {
    var self = null;
    Vue.component('main-sidebar-submenu', {
      name: 'main-sidebar-submenu',
      template: '\
        <el-submenu v-if="menu.children && menu.children.length >= 1" :index="menu.id" :popper-append-to-body="false">\
          <template slot="title">\
            <svg class="icon-svg aui-sidebar__menu-icon" aria-hidden="true"><use :xlink:href="\'#\' + menu.icon"></use></svg>\
            <span>{{ menu.name }}</span>\
          </template>\
          <main-sidebar-submenu v-for="item in menu.children" :key="item.id" :menu="item"></main-sidebar-submenu>\
        </el-submenu>\
        <el-menu-item v-else :index="menu.id" @click="gotoRouteHandle(menu.id)">\
          <svg class="icon-svg aui-sidebar__menu-icon" aria-hidden="true"><use :xlink:href="\'#\' + menu.icon"></use></svg>\
          <span>{{ menu.name }}</span>\
        </el-menu-item>\
      ',
      props: {
        menu: {
          type: Object,
          required: true
        }
      },
      beforeCreate: function () {
        self = this;
      },
      methods: {
        // 通过menuId与路由列表进行匹配跳转至指定路由
        gotoRouteHandle: function (menuId) {
          var route = win.SITE_CONFIG.routeList.filter(function (item) { return item.menuId === menuId })[0];
          if (route) {
            win.location.hash = route.name;
          }
        }
      }
    });
  })();

  /**
   * table-tree-column组件
   */
  (function () {
    var self = null;
    Vue.component('table-tree-column', {
      name: 'table-tree-column',
      template: '\
        <el-table-column :prop="prop" v-bind="$attrs">\
          <template slot-scope="scope">\
            <span @click.prevent="toggleHandle(scope.$index, scope.row)" :style="{ \'padding-left\': ((scope.row._level || 0) * 10) + \'px\' }">\
              <i :class="[ scope.row._expanded ? \'el-icon-caret-bottom\' : \'el-icon-caret-right\' ]" :style="{ \'visibility\': hasChild(scope.row) ? \'visible\' : \'hidden\' }"></i>\
              {{ scope.row[prop] }}\
            </span>\
          </template>\
        </el-table-column>\
      ',
      props: {
        prop: {
          type: String
        },
        treeKey: {
          type: String,
          default: 'id'
        },
        parentKey: {
          type: String,
          default: 'pid'
        },
        childKey: {
          type: String,
          default: 'children'
        }
      },
      beforeCreate: function () {
        self = this;
      },
      methods: {
        hasChild: function (row) {
          return (_.isArray(row[self.childKey]) && row[self.childKey].length >= 1) || false;
        },
        // 切换处理
        toggleHandle: function (index, row) {
          if (!self.hasChild(row)) {
            return false;
          }
          var data = self.$parent.store.states.data.slice(0);
          data[index]._expanded = !data[index]._expanded;
          if (data[index]._expanded) {
            row[self.childKey].forEach(function (item) {
              item._level = (row._level || 0) + 1;
              item._expanded = false;
            })
            data = data.splice(0, index + 1).concat(row[self.childKey]).concat(data);
          } else {
            data = self.removeChildNode(data, row[self.treeKey]);
          }
          self.$parent.store.commit('setData', data);
          self.$nextTick(function () {
            self.$parent.doLayout();
          })
        },
        // 移除子节点
        removeChildNode: function (data, pid) {
          var pids = _.isArray(pid) ? pid : [pid];
          if (pid.length <= 0) {
            return data;
          }
          var ids = [];
          for (var i = 0; i < data.length; i++) {
            if (pids.indexOf(data[i][self.parentKey]) !== -1 && pids.indexOf(data[i][self.treeKey]) === -1) {
              ids.push(data.splice(i, 1)[0][self.treeKey]);
              i--;
            }
          }
          return self.removeChildNode(data, ids);
        }
      }
    });
  })();
})();

// 解决四维运算,js计算失去精度的问题 
//加法   
Number.prototype.add = function(arg){   
    var r1,r2,m;
    if(isNaN(this)){this===0}
    if(isNaN(arg)){arg=0}
    try{r1=this.toString().split(".")[1].length}catch(e){r1=0}   
    try{r2=arg.toString().split(".")[1].length}catch(e){r2=0}   
    m=Math.pow(10,Math.max(r1,r2));
    return (this*m+arg*m)/m;
}     
//减法   
Number.prototype.sub = function (arg){   
    return this.add(-arg);   
}   
//乘法   
Number.prototype.mul = function (arg)   
{   if(isNaN(arg)){arg=0;}
	if(isNaN(this)){this===0}
    var m=0,s1=this.toString(),s2=arg.toString();   
    if(s1.indexOf(".")!=-1){
	    try{m+=s1.split(".")[1].length}catch(e){console.log(e)}   
    }
    if(s2.indexOf(".")!=-1){
	    try{m+=s2.split(".")[1].length}catch(e){console.log(e)}
    }
    //alert(Number(s1.replace(".","")) +  '   -   '+ Number(s2.replace(".",""))  + "   -   "   + m );
    var v=Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);   
    //console.log(v);
    return v;
}  
//除法   
Number.prototype.div = function (arg){   
    var t1=0,t2=0,r1,r2;   
    try{t1=this.toString().split(".")[1].length;}catch(e){}   
    try{t2=arg.toString().split(".")[1].length;}catch(e){}   
    with(Math){   
        r1=Number(this.toString().replace(".",""))   
        r2=Number(arg.toString().replace(".",""))   
        return (r1/r2)*pow(10,t2-t1);   
    }   
}
