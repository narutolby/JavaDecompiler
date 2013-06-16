/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-12
 * Time: 上午9:34
 * To change this template use File | Settings | File Templates.
 */
(function ($, exports) {
    $(function () {
        $("#Filedata").commonUpload({"width":"157px", "height":"45px", "onUploadSuccess":function (file, data, response) {
            var fileName = file.name;
            $("#file-queue ul").append("<li><a href='resourceOperation/download.html?resourceId=" + data + "'>" + fileName + "</a></li>");
            /*$("#title").val(fileName);*/
            $("#summary").val(fileName);
            uploadResource.resourceIds.push(data);
        },
            overrideEvents:["onUploadStart"],
            onUploadStart:function (filesSelected, filesQueued, queueLength) {
                ViewController.trigger("toggleActive", step2Controller);
                var $myDirectory = $("#myDirectory");
                var _this = this;
                if (typeof _this.courseCache != "undefined") {
                    var options = "";
                    var _courseCache = _this.courseCache;
                    for (var index in _courseCache) {
                        var _course = _courseCache[index];
                        options += "<option value='" + _course.id + "'>" + _course.name + "</option>";
                    }
                    $myDirectory.html(options);
                    return;
                }
                $.getJSON("user/my_configure.json", function (data) {
                    var $myDirectory = $("#myDirectory");
                    _this.courseCache = [];
                    data = data.majorMap;
                    var options = "";
                    for (var key in data) {
                        var _value = data[key];
                        for (var index in _value) {
                            var _course = _value[index];
                            _this.courseCache.push(_course);
                            options += "<option value='" + _course.id + "'>" + _course.name + "</option>";
                        }
                    }
                    $myDirectory.html(options);
                });
            }
        });
    });
    var ViewController = {
        eventSource:$({}),
        create:function (view) {
            var klass = function () {
            };
            klass.prototype.active = function () {
                $(view).show();
            };
            klass.prototype.deactive = function () {
                $(view).hide();
            };
            var clazz = new klass;
            this.add(clazz);
            return clazz;
        },
        add:function (controller) {
            this.eventSource.bind("toggleActive", function (event, _controller) {
                if (controller == _controller) {
                    controller.active()
                } else {
                    controller.deactive();
                }
            });
            return this;
        },
        trigger:function (eventType, _controller) {
            this.eventSource.trigger(eventType, [_controller])
        }
    };
    var uploadResource = {
        resourceIds:[],
        description:"",
        courseIds:"",
        save:function () {
            $.post("resourceOperation/save.json", {resourceIds:this.resourceIds.join(","), description:this.description, courseIds:this.courseIds}, function (data) {
                  if(data.save=="success"){
                      $.messager.alert("亲，提交成功啦，快去我的资源看看吧～") ;
                  }else{
                      $.messager.alert("亲，出错了，一会再试哈～") ;
                  }
            });
        },
        init:function(){
            $("#submitBtn").click(function(){
                var _uploadResource = uploadResource;
                var $myDirectory = $("#myDirectory");
                if($myDirectory.val()=="" || $myDirectory.val()==null){
                    $.messager.alert("亲，记得要填文件夹哦～");
                    return;
                }
                _uploadResource.description = $("#summary").val();
                _uploadResource.courseIds = $myDirectory.val().join(",");
                _uploadResource.save();
            });
        }
    };
    var step1Controller = ViewController.create("#step1");
    var step2Controller = ViewController.create("#step2");
    uploadResource.init();
})(jQuery, window);
