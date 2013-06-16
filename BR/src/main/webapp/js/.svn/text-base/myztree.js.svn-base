(function($,exports){
    function clone(jsonObj, newName) {
        var buf;
        if (jsonObj instanceof Array) {
            buf = [];
            var i = jsonObj.length;
            while (i--) {
                buf[i] = clone(jsonObj[i], newName);
            }
            return buf;
        }else if (typeof jsonObj == "function"){
            return jsonObj;
        }else if (jsonObj instanceof Object){
            buf = {};
            for (var k in jsonObj) {
                if (k!="parentNode") {
                    buf[k] = clone(jsonObj[k], newName);
                    if (newName && k=="name") buf[k] += newName;
                }
            }
            return buf;
        }else{
            return jsonObj;
        }
    }
    /*
     var setting1 = {
     */
    /*edit: {
     *//*
     */
    /*drag:{
     isCopy:true,
     isMove:false
     },*//*
     */
    /*
     enable: true,
     showRemoveBtn: false,
     showRenameBtn: false,


     },*//*

     edit: {
     drag:{
     isCopy:true,
     isMove:false
     },
     enable: true,
     showRemoveBtn: false,
     showRenameBtn: false


     },
     data: {
     simpleData: {
     enable: true
     }
     },
     callback: {
     beforeDrag: beforeDrag,
     beforeDrop: beforeDrop
     }
     };

     var setting2 = {
     */
    /*edit: {
     drag:{
     isCopy:false,
     isMove:false
     },
     enable: true,
     showRemoveBtn: true,
     showRenameBtn: false,
     dragCopy: true,
     dragMove: true


     },*//*

     edit: {
     drag:{
     isCopy:true,
     isMove:false
     },
     enable: true,
     showRemoveBtn: false,
     showRenameBtn: false


     },
     data: {
     simpleData: {
     enable: true
     }
     },
     callback: {
     beforeDrag: beforeDrag,
     beforeDrop: beforeDrop
     }
     };

     var zNodes =

     $(document).ready(function(){
     $.fn.zTree.init($("#ztree1"), setting1, zNodes);
     $.fn.zTree.init($("#ztree2"), setting2);

     });*/

    var zNodes;
    var zNodes1;
    var zTree1, zTree2;
    var setting;

    setting = {
        editable: true,
        edit_renameBtn:false,
        edit_removeBtn:false,
        dragCopy: true,
        dragMove: true,
        callback: {
            click:	zTreeOnClick,
            drop: zTreeOnDrop
        }
    };

    $(document).ready(function(){
        $.getJSON("academy/all.json",function(data){
            zNodes = data.allMajors;
            zNodes1 = data.userMajors;
            reloadTree();
        });
        bindSubmitEvent();
    });

    var preSelectedNode1;
    var preSelectedNode2;

    function zTreeOnClick(event, treeId, treeNode) {
        if (treeId=="treeDemo") {
            if (preSelectedNode1 == treeNode) {
                zTree1.cancelSelectedNode();
                preSelectedNode1 = null;
            } else {
                preSelectedNode1 = treeNode;
            }
        } else {
            if (preSelectedNode2 == treeNode) {
                zTree2.cancelSelectedNode();
                preSelectedNode2 = null;
            } else {
                preSelectedNode2 = treeNode;
            }
        }
    }

    function zTreeOnDrop(event, treeId, treeNode, targetNode, moveType) {
        preSelectedNode1 = zTree1.getSelectedNode();
        preSelectedNode2 = zTree2.getSelectedNode();
    }

    exports.moveTreeL2R = function() {
        moveTreeNode(zTree1, zTree2);
    }

    exports.moveTreeR2L = function () {
        var selectNode = zTree2.getSelectedNode();
        zTree2.removeNode(selectNode);
        //moveTreeNode(zTree2, zTree1);
    }

    function moveTreeNode(srcTree, targetTree) {
        var srcNode = srcTree.getSelectedNode();
        if (!srcNode) {
            alert("请先选择需要移动的节点！");
            return;
        }
        // var targetNode = targetTree.getSelectedNode();
        //srcTree.removeNode(srcNode);
        targetTree.addNodes(null, [clone(srcNode)]);
        //targetTree.selectNode(srcNode);
    }

    function reloadTree() {
        zTree1 = $("#majorTree").zTree(setting, clone(zNodes));
        zTree2 = $("#selectMajorTree").zTree(setting,clone(zNodes1));

    };

    function bindSubmitEvent(){
        var $submitBtn = $("#submitBtn").click(function(){
            var selectNodesMap = {};
            var nodes = zTree2.getNodes();
            getNodesWithCursive(nodes,selectNodesMap);
            var majorsArray  = $.map(selectNodesMap,function(value,key){
                return key;
            });
            jQuery.post("academy/set_major.json",{"majors[]":majorsArray},function(data){

            });
          //  alert(majorsArray);
        });

        var getNodesWithCursive = function(nodes,selectNodesMap){
              for(var index in nodes){
                  var node = nodes[index];
                  if(typeof node.nodes !== "undefined"){
                      arguments.callee(node.nodes,selectNodesMap);
                  }else{
                      selectNodesMap[node.ename]  = node.name;
                  }
              }

        }
    }
})(jQuery,window);
