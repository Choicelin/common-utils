
$(function () {
    // var editor;
    //
    var today = new Date().getFullYear()+"-"+PrefixInteger(new Date().getMonth()+1,2)+"-"+PrefixInteger(new Date().getDate(),2);


    var table = $("#saleTable").DataTable({
        ajax: {
            url: "/report/getSaleData/"+today,
            async:false
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
            {
                className:      'details-control',
                orderable:      false,
                data:           null,
                defaultContent: '关联会员'
            },
            {data: "orderId"},
            {data: "barCode"},
            {data: "memberMobile"},
            {data: "colorName"},
            {data: "sizeName"},
            {data: "salePrice"},
            {data: "createTime"}

        ],

        "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api(), data;

            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?
                        i : 0;
            };

            // Total over all pages
            total = api
                .column( 6 )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );


            // Update footer
            $( api.column( 6 ).footer() ).html(
                '￥'+ total
            );
        }


        // buttons: [
        //     { extend: "create", text:"新建", editor: editor },
        //     { extend: "edit",  text:"修改", editor: editor }
        // ]
    });


    $("#beginSearch").on("click",function (event) {
       var begin  = $("#searchBegin").val();
       var end  = $("#searchEnd").val();
       var Year = new Date().getFullYear();
       var beginDate  = Year+"-"+begin;
       var endDate  = Year+"-"+end;
       table.ajax.url('/report/getSaleDataWithBeginEnd/'+beginDate+'/'+endDate).load();
    });


    function PrefixInteger(num, length) {
        return (Array(length).join('0') + num).slice(-length);
    }


    var orderId = 0;

    $('#saleTable tbody').on('click','td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
        orderId = row.data().orderId;
        $('#contactMember').modal('show');

    } );


    var memberId =0;

    $("#searchMember").on("click",function (event) {
        var word = $("#memberCard").val();
        $.ajax({
            url: "/member/getMember/" + word,
            success: function (datas) {
                var jsonData = JSON.parse(datas)['object'];
                memberId = jsonData['id'];
                var memberInfo = "会员名：" + jsonData['memberName'] + " 手机号：" + jsonData["memberMobile"]
                    + " 积分：" + jsonData["memberScores"] + " 余额：" + jsonData["memberCash"] + " 会员折扣：" + jsonData["memberDiscount"];
                $("#showMemberInfo").text(memberInfo);

            }
        })
    });

    $("#submitContact").on("click",function () {
        $.ajax({
            url: "/report/contactMember/" + memberId+"/"+orderId,
            success: function (datas) {
                $('#contactMember').modal('hide');
                window.location.reload();
            }
        })
    });

});


