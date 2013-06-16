/**
 * Created with JetBrains PhpStorm.
 * User: zhaomeng
 * Date: 13-3-5
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */

//handleData(10,10);
var Times ,XMLcontent='',callback;
function handleData(times,size,fun){
    Times=times;
    callback=fun;
    var start= 0;
    for(var i=0;i<times;i++){
        requestData(i,start,size,function(content){
            XMLcontent=XMLcontent+"\n"+content;
        });
        start+=size;
    }
}

function requestData(i,start,size,fun){
    $.ajax({
        type: 'get',
        url: "http://localhost:8080/1.xml?start="+start+"&size="+size+"&apiType=jsonp",
        dataType: 'json',
        //jsonp:"jsoncallback",
        success:function(data){
            content = jsonToXML(data);
            fun(content);
            if(i==Times-1){
                callback(assembleXML(XMLcontent));
            }
        },
        async:false,
        error: function(error){
            alert("数据请求出错~ "+error);
            return false;
        }
    });
}

function jsonToXML(data){
    var resData=data.food_list,
        o,brand,name,producor,sample_time,unquality_part,user_name,tempXML='';
    for(var i=0;i<resData.length;i++){
        o=resData[i];
        brand=o.brand;
        name=o.name;
        producor=o.producor;
        sample_time=o.sample_time;
        unquality_part=o.unquality_part;
        user_name= o.user_name;

        tempXML+='<item>'+'\n'+
            '<key>'+name+'</key>'+'\n'+
            '<display>'+'\n'+
            '<url><![CDATA[http://www.jike.com/shipin/dirty/so?q='+name+']]></url>'  +'\n'+
            '<title>'+name+'--不合格食品公示【即刻食品安全】</title>' +'\n'+
            ' <showurl><![CDATA[www.jike.com]]></showurl>' +'\n'+
            '<date>'+sample_time+'</date>'+'\n'+
            '<colcount>6</colcount>'+'\n'+
            '<form col0="品牌" col1="食品" col2="不合格项" col3="生产厂商" col4="经销商" col5="不合格详情" />'+'\n'+
            '<form col0="'+brand+'" col1="'+name+'" col2="'+unquality_part+'" col3="'+producor+'" col4="'+user_name+'" col5="不合格详情" col5link="http://www.jike.com/shipin/dirty/so?q='+name+'" />' +'\n'+
            '<link linkurl="http://www.jike.com/shipin"  linkcontent="即刻食品安全提供权威数据" />'+'\n'+
            '<docicon doctips="不合格食品信息来自国家权威检测机构，即刻整理完成。" dociconaddress="http://open.baidu.com/stat/sfda.gif"  dociconlink="http://www.jike.com/shipin" />'+'\n'+
            '</display>'+'\n'+
            '</item>'+'\n';

    }
    return tempXML;
}

function assembleXML(XMLcontent){
    var XMLdoc='<?xml version="1.0" encoding="gbk" ?>'+'\n'+
        '<DOCUMENT>'+'\n'+
        XMLcontent+'\n'+
        '</DOCUMENT>';
    return XMLdoc;
}

