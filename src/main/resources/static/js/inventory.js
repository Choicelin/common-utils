$(function () {

    function getSearchParams() {
        var temp = {
            onlyStore :1,
            onyBoy:1
        }
    }

    $("#inventoryTable").bootstrapTable({
        url: '/inventory/getAllProduct',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        //sortable: false,                     //是否启用排序
        //sortOrder: "asc",                   //排序方式
        queryParams: getSearchParams(),//传递参数（*）
        //sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "barCode",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        onEditableSave: function (field, row, oldValue, $el) {

            console.log(row);

            var product = row;
            $.ajax({
                type: "post",
                url: "/inventory/updateProduct",
                data: JSON.stringify(row),
                contentType: "application/json",
                success: function (data) {

                }

            });
        },
        columns: [
            {
                field:'productImage',
                title:'照片',
                formatter:function (index, value, row) {
                    var src  = "http://file.demengsite.cn/"+value['productImag'];
                    // var content = "<img src='"+src+"' height = '300px'  width = '200px' >";
                    return '<img class="thumbnail" name="productImage"   height="50px" width="50px" src=" '+src+' ">'+
                        '<script>'+
                        '$("img[name=productImage]").on("mouseenter",function (event) {'+
                          '  var src  = $(event.currentTarget).attr("src");var content = "<img src="+src+" height=300px width=200px>";'+
                            '$(event.currentTarget).tooltip({title: content,html:true,placement:"right"})});'+
                        // '$("img[name=productImage]").popover("show");
                    '</script>';
                }
            },

            {
                field: 'barCode',
                title: '条码'
            },
            {
                field: 'productId',
                title: '货号'
            }, {
                field: 'colorName',
                title: '颜色'
            }, {
                field: 'sizeName',
                title: '尺码'
            }, {
                field: 'originalPrice',
                title: '吊牌价'
            }, {
                field: 'salePrice',
                title: '售价',
                editable: true
            }, {
                field: 'productNum',
                title: '数量',
                editable: true
            }]
    });


    $("#excelFile").fileinput({
        language: 'zh', //设置语言
        uploadUrl: '/inventory/importProduct' //上传的地址
        // allowedFileExtensions: ['xls', 'xlsx', 'png'],//接收的文件后缀
        // showUpload: true, //是否显示上传按钮
        // showCaption: false,//是否显示标题
        // browseClass: "btn btn-primary", //按钮样式
        // //dropZoneEnabled: false,//是否显示拖拽区域
        // //minImageWidth: 50, //图片的最小宽度
        // //minImageHeight: 50,//图片的最小高度
        // //maxImageWidth: 1000,//图片的最大宽度
        // //maxImageHeight: 1000,//图片的最大高度
        // //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        // //minFileCount: 0,
        // maxFileCount: 10, //表示允许同时上传的最大文件个数
        // enctype: 'multipart/form-data',
        // validateInitialCount:true,
        // previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        // msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    });

    $("#imageFile").fileinput({
        language: 'zh',
        uploadUrl: '/inventory/uploadImage',
        maxFileCount: 300 //表示允许同时上传的最大文件个数

    })


    // $("#productImage").hover(function (event) {
    //     $("#modal-image").modal('show');
    // },function (event) {
    //     $("#modal-image").modal('hide');
    // });

    // $('body').on('mouseenter mouseleave',"#productImage",function (event) {
    //
    //     if(event.type === "mouseenter"){
    //         //鼠标悬浮
    //         var srccc = $(event.currentTarget).attr("src");
    //         console.log("in");
    //         $("#showProductImage").attr("src",srccc);
    //     }else if(event.type === "mouseleave"){
    //         //鼠标离开
    //         console.log("out");
    //         $("#showProductImage").attr("src",'');
    //
    //     }
    // })
    // $("#productImage").popover({
    //     content:"aaaaa"
    // })

});