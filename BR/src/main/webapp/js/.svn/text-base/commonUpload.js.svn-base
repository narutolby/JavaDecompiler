/*
 *  @author lby
 *  @date 2012- 7-23
 */
    (function($) {
        $.extend($.fn,{
            commonUpload:function(options){
                if(this.length == 0){
                    return this;
                }
                var bindUpload = function(){
                    var defaultSetting = {
                        swf : 'js/uploadify/uploadify.swf',
                        uploader : '../resourceOperation/upload.html?user='+user,
                        removeCompleted : true,
                        buttonText : '',
                        fileSizeLimit : '30MB',
                        formData : {'Keyword':' '},
                        preventCaching:false,
                        onUploadSuccess : function(file, data, response) {
                        },
                        onUploadError : function(file, errorCode, errorMsg,
                                                   errorString) {
                            $.messager.alert('The file ' + file.name
                                + ' could not be uploaded: ' + errorString);
                        }
                    };
                    var setting = $.extend({},defaultSetting,options);
                    this.uploadify(setting);
                };
                return this.each(function(){
                    var $this =  $(this);
                    if(this.tagName.toLowerCase!="input" && $this.attr("id")==""){
                        throw Error("dom必须为input且id不能为空");
                        return;
                    }
                    bindUpload.apply($this);

                });
            }
        });
    })(jQuery);
