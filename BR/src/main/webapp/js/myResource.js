/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-11
 * Time: 下午1:47
 * To change this template use File | Settings | File Templates.
 */
(function ($, exports) {
    $(function () {
        var myDirectory = {id:"root", children:[]};
        $(".pagi").pagination("a:has(b)", "click", "elec_resrc/my_resrc.json", "myResourceTemplate", "resrc_list", 12);
        var $doc_type = $("#doc_type").delegate("a", "click", function () {
            $doc_type.find(".selected_type").removeClass("selected_type");
            var $this = $(this).addClass("selected_type");
            var resourceType = $this.attr("id");
            myResource.resourceType = resourceType;
            myResource.currentPage = 1;
            myResource.refresh();
        });
        var $doc_sort = $("#doc_sort").delegate(".btn_kind", "click", function () {
            var $this = $(this);
            if ($this.hasClass("btn_sortDesc")) {
                $this.removeClass("btn_sortDesc").addClass("btn_sortAsc");
                myResource.order = $this.attr("id") + "_asc";
            } else if ($this.hasClass("btn_sortAsc")) {
                $this.removeClass("btn_sortAsc").addClass("btn_sortDesc");
                myResource.order = $this.attr("id") + "_desc";
            } else {
                $this.addClass("btn_sortDesc");
                myResource.order = $this.attr("id") + "_desc";
            }
            $this.siblings(".btn_kind").attr("class", "btn_kind");
            myResource.currentPage = 1;
            myResource.refresh();
        });
        $.getJSON("user/my_configure.json", function (data) {
            var $folder_parent = $("#folder_parent");
            var folder_nodes = "";
            data = data.majorMap;
            for (var key in data) {
                var a = key.split("#_#");
                var id = a[0], name = a[1];
                folder_nodes += "<div class='folder folder_nodes'><p class='folder-nodes-icon' id='" + id + "'>" + name + "</p></div>";
                var majorFolders = {id:id, name:name, children:data[key], type:"major"};
                myDirectory.children.push(majorFolders);
            }
            $folder_parent.parent("div").append(folder_nodes);
            $("#bidFolder").html($.tmpl("folder_template", myDirectory.children));
        });
        $("#aside-content").delegate("p", "click", function () {
            $(this).parent(".folder").addClass("folder_selected").siblings().removeClass("folder_selected");
            var _id = $(this).attr("id");
            var $bigFolder = $("#bidFolder");
            closeFolder();
            if (_id === "root") {
                $bigFolder.html($.tmpl("folder_template", myDirectory.children));
                $("#upload-resource").show();
            } else {
                var _children = myDirectory.children;
                for (var i in _children) {
                    if (_children[i]["id"] === _id) {
                        _children = _children[i].children;
                        break;
                    }
                }
                $bigFolder.html($.tmpl("folder_template", _children));
                $("#upload-resource").hide();
            }
        });
        $("#bidFolder").delegate("li", "click", function () {
            var $this = $(this);
            var _id = $this.attr("id");
            var _type = $this.attr("type");
            var $bigFolder = $("#bidFolder");
            if (_type === "major") {
                var _children = myDirectory.children;
                for (var i in _children) {
                    if (_children[i]["id"] === _id) {
                        _children = _children[i].children;
                        break;
                    }
                }
                $bigFolder.html($.tmpl("folder_template", _children));
                $("#aside-content").find("#" + _id).trigger("click");
            } else {
                myResource.courseId = _id;
                openFolder();
            }
        });
        var openFolder = function () {
            $(".bg_position").removeClass("bg_positionBorder");
            $(".doc_type").show();
            myResource.resourceType = "all";
            $("#doc_sort").find("#uploadTime").trigger("click").end().show();
            $("#bidFolder").hide();
            $("#pagi").show();
            $("#folder_operation").hide();
            var $docType = $("#doc_type").find(".selected_type").removeClass("selected_type").end();
            $docType.find("#all").addClass("selected_type");
        };
        var closeFolder = function () {
            $(".bg_position").addClass("bg_positionBorder");
            $("#bidFolder").show();
            $("#pagi").hide();
            $("#folder_operation").show();
            $(".doc_type").hide();
            $("#doc_sort").hide();
            $("#resrc_list").empty();
        };
        exports.getImg = function ($item) {
            var _childrenCount = $item.childrenCount;
            if (typeof _childrenCount == "undefined" || _childrenCount!=0) {
                return "img/icon_fullFolder.png";
            }else{
                return "img/icon_emptyFolder.png";
            }
        };

        var folder_template = '<li id="${id}" type="${type}"><div><img src="${getImg($item.data)}"/></div>' +
            '<div><input type="checkbox" name="folderCheck" class="valign"/><span>${name}</span></div></li>';
        $.template("folder_template", folder_template);    });

})(jQuery, window);


(function ($, exports) {



})(jQuery, window);