
var editor;

editor = new $.fn.dataTable.Editor( {
    ajax: function (method, url, d, successCallback, errorCallback) {

        var output = { data: [] };
        if ( d.action === 'create' ) {
            $.each( d.data, function (key, value) {

                $.ajax({
                    url:"/member/newMember",
                    type:"post",
                    async:false,
                    data:value,
                    success:function (datas) {
                        var memberInfo = JSON.parse(datas)["object"];
                        value.id = memberInfo["id"]
                    }
                });
                output.data.push( value );
            } );
        }else if ( d.action === 'edit' ) {
            $.each( d.data, function (id, value) {
                value.id =id;
                $.ajax({
                    url:"/member/updateMember",
                    type:"post",
                    async:false,
                    data:value,
                    success:function (datas) {
                        alert("修改成功");
                    }
                });

                output.data.push( value );

            } );
        }

        successCallback( output );

    },
    table: "#memberTable",
    idSrc:  'id',
    fields:[
        {
          label:"卡号",
          name:"cardId"
        },
        {
          label:"卡名",
          name:"cardName",
          def:"德蒙钻石卡"
        },
        {
          label:"会员名",
          name:"memberName"
        },
        {
          label:"会员手机号",
          name:"memberMobile"
        },
        {
          label:"孩子姓名",
          name:"childrenName"
        },
        {
            label:"孩子生日",
            name:"childrenBirthday",
            type:  'datetime',
            def:   function () { return new Date(); }
        },
        {
          label:"会员余额",
          name:"memberCash"
        },
        {
          label:"会员积分",
          name:"memberScores"
        }
    ]
} );


var table = $("#memberTable").DataTable({
    dom: "Bfrtip",
    select: {
        style:    'os',
        selector: 'td:first-child'
    },
    responsive: true,
    oLanguage : {
        "sLengthMenu": "每页显示 _MENU_ 条记录",
        "sZeroRecords": "抱歉， 没有找到",
        "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
        "sInfoEmpty": "没有数据",
        "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
        "sSearch": "名称:",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "前一页",
            "sNext": "后一页",
            "sLast": "尾页"
        }

    },
    columns: [
        // {
        //     data: null,
        //     defaultContent: '',
        //     className: 'select-checkbox',
        //     orderable: false
        // },
        {
            className:      'details-control',
            orderable:      false,
            data:           null,
            defaultContent: '修改点我'
        },
        {data: "cardId"},
        {"data": "cardName"},
        {"data": "memberName"},
        {"data": "memberMobile"},
        {"data": "memberCash"},
        {"data": "memberScores"},
        {"data": "buyCount"},
        {"data": "totalBuy"}
    ],
    ajax: {
        url: "/member/getAllMember",
        async:false
    },

    buttons: [
        { extend: "create", text:"新建", editor: editor },
        { extend: "edit",  text:"修改", editor: editor }
    ]
});

// Add event listener for opening and closing details
$('#memberTable tbody').on('click', 'td.details-control', function () {
    var tr = $(this).closest('tr');
    var row = table.row( tr );

    if ( row.child.isShown() ) {
        // This row is already open - close it
        row.child.hide();
        tr.removeClass('shown');
    }
    else {
        // Open this row
        //根据会员id查询会员的消费消息
        var memberId  = row.data().id;
        var tableHtml = '<table>';
        $.ajax({
            url:"/member/getMemberSale/"+memberId,
            type:"GET",
            dataType:'json',
            async:false,
            success:function (datas) {
                var saleList = datas['object'];

                for (var sale in saleList){
                    var saleInfo  = saleList[sale];
                    tableHtml+= '<tr>'+
                        '<td>'+'货号：'+saleInfo.barCode+'</td>'+
                        '<td>'+'颜色：'+saleInfo.colorName+'</td>'+
                        '<td>'+'尺码：'+saleInfo.sizeName+'</td>'+
                        '<td>'+'价格：'+saleInfo.salePrice+'</td>'+
                        '</tr>'
                }
            }
        });
        tableHtml+='</table>';
        row.child( tableHtml ).show();
        tr.addClass('shown');
    }
} );



