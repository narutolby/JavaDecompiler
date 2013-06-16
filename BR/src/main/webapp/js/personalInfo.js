/**
 * Created with IntelliJ IDEA.
 * User: zhaomeng
 * Date: 12-12-14
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
(function ($, exports) {
    var personalController = {
        elements:{
            userId:"#userId",
            passwd:"#passwd",
            passwdConfirm:"#passwdConfirm",
            email:"#email",
            submit:"#submit",
            form:"#form"
        },
          validator:{
            shouldSubmit:true,
            passwd:function(){
               return /.{4,16}/.test(this.passwd.val());
            },
            passwdConfirm:function(){
                return  /.{4,16}/.test(this.passwd.val()) && this.passwd.val()===this.passwdConfirm.val();
            },
            email:function(){
                return /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(this.email.val());
            }
          }
        ,
        events:{
            common:{
                focus:function(event,_this){
                    var $t = $(event.target);
                   _this.form.find("span.hide").hide();
                   $t.next("span.msgPlace").show();
                },
                blur:function(_input,_this){
                    var _msg = _this[_input].next(".msgPlace");
                    if($.trim(_this[_input].val())==""){
                       _msg.removeClass().addClass("msgPlace hide").html(_msg.attr("msg"));
                        personalController.validator.shouldSubmit=true;
                       return;
                    }
                    if(personalController.validator.shouldSubmit=$.proxy(personalController.validator[_input],_this)()){
                       _msg.removeClass().addClass("msgPlace success").attr("msg",_msg.text()).empty();
                    }else{
                       _msg.removeClass().addClass("msgPlace error").empty();
                    }
                }
            },
            passwd:{
               blur:function(){
                   personalController.events.common.blur("passwd",this);
               },
               focus:function(event){
                   personalController.events.common.focus(event,this);
               }
            } ,
            passwdConfirm:{
                blur:function(){
                    personalController.events.common.blur("passwdConfirm",this);
                },
                focus:function(event){
                    personalController.events.common.focus(event,this);
                }
            },
            email:{
                blur:function () {
                    personalController.events.common.blur("email",this);
                },
                focus:function(event){
                    personalController.events.common.focus(event,this);
                }
            },
            submit:{
                click:(function(){
                    var timer;
                    return function(){
                    if(!this.validator.shouldSubmit){
                        return false;
                    }
                    $.get("user/modify.json",{passwd:this.passwd.val(),email:this.email.val()}, $.proxy(function(data){
                       var _msg = this.submit.next("span.msgPlace");
                       if(data.submit){
                         _msg.css("display")=="none" && _msg.css("color","green").text("修改成功!").show();
                       }else{
                         _msg.css("display")=="none" &&  _msg.css("color","red").text("修改失败!").show();
                       }
                        clearTimeout(timer);
                       timer = setTimeout(function(){
                           _msg.hide();
                       },5000);

                    },this));
                    };
                })()
            }
        },
        init:function () {
            var _elements = this.elements;
            for(var ele in _elements){
               this[ele]=$(_elements[ele]);
               for(var eve in this.events[ele]){
                   this[ele].bind(eve, $.proxy(this.events[ele][eve],this));
               }

            }

        }
    };
    $(function () {
        personalController.init();
    });

})(jQuery, window);

(function ($, exports) {
    $("#aside-content").delegate("p", "click", function () {
        $(this).parent(".folder").addClass("folder_selected").siblings().removeClass("folder_selected");
        if($(this).parent(".folder").attr("id")=="folder_parent"){
            $("#basic-info").show().siblings().hide();
        }else{
            $("#"+$(this).attr("block")).show().siblings().hide();
        }
    });
})(jQuery, window);