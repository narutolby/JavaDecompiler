/**
 *
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-4-1
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */
(function (exports) {
    var Obj = {
        get:(function () {
            var klass = function () {
                this.array = [];
            };
            klass.prototype.removeClass = function (t_className) {
                var _a = this.array;
                for (var i = 0; i < _a.length; i++) {
                    var f_className = t_className.split(" ");
                    for (var j = 0; j < f_className.length; j++) {
                        if (f_className[j] == t_className) {
                            f_className.splice(i, 1);
                            _a[i].className = f_className.join(" ");
                            break;
                        }
                    }

                }
                return this;
            };
            klass.prototype.addClass = function (t_className) {
                var _a = this.array;
                for (var i = 0; i < _a.length; i++) {
                    var f_className = t_className.split(" ");
                    f_className.push(t_className);
                    _a[i].className(f_className.join(" "));
                }
                return this;
            };
            return function (dom) {
                var clazz = new klass();
                if (dom.nodeType) {
                    clazz.array.push(dom);
                } else if (dom.length) {
                    Array.prototype.push.apply(clazz.array, dom);
                } else if (dom.indexOf("#") == 0) {
                    clazz.array.push(document.getElementById(dom.substring(1)));
                } else if (dom.indexOf(".") == 0) {
                    Array.prototype.push.apply(clazz.array, document.getElementsByClassName(dom.substring(1)));
                } else if (typeof(dom) == "string") {
                    Array.prototype.push.apply(clazz.array, document.getElementsByTagName(dom.substring(1)));
                }
                return clazz;
            }
        })()
    };
    var _d = exports.document;
    if (_d.addEventListener) {
        _d.addEventListener("DOMContentLoaded", function () {
            var doms = Obj.get(".jike-b-input").array;
            for (var i = 0; i < doms.length; i++) {
                var _parent = Obj.get(doms[i].getParentNode());
                doms[i].onfocus = function () {
                    _parent.removeClass("jike-b-inputWrap").addClass("jike-b-inputFocus");
                };
                doms[i].onblur = function () {
                    _parent.removeClass("jike-b-inputFocus").addClass("jike-b-inputWrap");
                }
            }
            doms = Obj.get(document.querySelectorAll(".jike-b-msg li"));
            for(var j=0;j<doms.array.length;j++){
                var _a = doms.array[j];
                if(j==0){
                    _a.onmouse
                }
            }
        });

    }
})
    (window);
