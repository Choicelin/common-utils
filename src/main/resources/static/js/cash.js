$(function () {

    $.ajaxSetup({
        cache: false
    });

    var memberCash = 0;
    var memberScores = 0;

    $("input[name=memberRadio]").on("click", function () {
        switchMember()
    });


    var backChange = 0;

    var productTable = $('#productListTable').bootstrapTable({

        toolbar: '#toolbar',
        clickToSelect: true,
        uniqueId:'uid',

        onEditableSave: function (field, row, oldValue, $el) {
            var id_value ;
            $('input[name="btSelectItem"]:checked').each(function () {
                id_value= $(this).data('index');
            });
            if (field === 'discount') {
                productTable.bootstrapTable("updateCell",
                    {
                        index: id_value,
                        field: 'salePrice',
                        value: Math.round(row['originalPrice'] * row['discount'])
                    });
            } else if (field === 'salePrice') {
                productTable.bootstrapTable("updateCell",
                    {
                        index: id_value,
                        field: 'discount',
                        value: (row['salePrice'] / row['originalPrice']).toFixed(2)
                    });
            }
            calTotalMoney();

        },
        columns: [
            {radio:true},
            {
                field:'uid',
                title:'编号'
            },
            {
                field: 'barCode',
                title: '条码'
            },
            {
                field: "productId",
                title: '代码'
            },
            {
                field: "productName",
                title: '名称'
            },
            {
                field: 'colorName',
                title: '颜色'
            }, {
                field: 'sizeName',
                title: '尺码'
            },

            {
                field: 'originalPrice',
                title: '吊牌价'
            },
            {
                field: "discount",
                title: '折扣',
                editable: true
            },
            {
                field: 'salePrice',
                title: '售价',
                editable: true
            }
        ]
    });


    $("#btn_delete").on('click',function (event) {
        var id_value = productTable.bootstrapTable('getSelections')[0]['uid'];
        productTable.bootstrapTable('removeByUniqueId',id_value);
        calTotalMoney();
    });

    var discount = 1;
    var totalPrice = 0;
    var totalCount = 0;

    $("#newMemberButton").on("click", function (event) {
        var rechargeValue = $("#reChargeValue").val();
        var id = $("div[data-name=memberCardId]").attr("data-value");
        var url = "/member/recharge?rechargeValue=" + rechargeValue + "&id=" + id;
        $.ajax({
            url: url,
            success: function (datas) {
                reNewMemberInfo();
            }
        })
    });


    //录入一个新商品
    $("#productBarCode").on("keyup", function (event) {
        if (event.keyCode === 13) {

            var barCode = $(event.currentTarget).val();


            $.ajax({
                url: "/product/getProductBybarCode/" + barCode.trim(),
                async: false,
                success: function (datas) {
                    datas = JSON.parse(datas);
                    datas.discount = (datas['salePrice'] / datas['originalPrice']).toFixed(2);
                    datas.buyNum = 1;
                    var length = productTable.bootstrapTable('getData').length;
                    datas.uid=length;
                    productTable.bootstrapTable('insertRow', {
                        index: barCode,
                        row: datas
                    });

                }
            });


            $("#productBarCode").val("");

            calTotalMoney();


        }
    });

    //输入会员手机号后按回车
    $("#memberCard").on("keyup", function (event) {
        if (event.keyCode === 13) {
            reNewMemberInfo()
        }
    });

    $("input[name=getSubmit]").on("keyup", function (event) {

        calChange();
    });

    $("input[name=getSubmit]").on("blur", function (event) {
        if ($(this).val() === '') {
            $(this).val(0);
        }
        calChange();
    });
    $("input[name=getSubmit]").on("focus", function (event) {
        $(this).val('');
        calChange();
    });


    //获取会员信息，并更新折扣
    function reNewMemberInfo() {
        var word = $("#memberCard").val();
        //输入会员卡号或者手机号之后按回车
        $.ajax({
            url: "/member/getMember/" + word,
            success: function (datas) {
                var jsonData = JSON.parse(datas)['object'];
                $("div[data-name=memberCardId]").attr("data-value", jsonData["id"]);
                memberCash = jsonData["memberCash"];
                memberScores = jsonData["memberScores"];
                var memberInfo = "会员名：" + jsonData['memberName'] + " 手机号：" + jsonData["memberMobile"]
                    + " 积分：" + jsonData["memberScores"] + " 余额：" + jsonData["memberCash"] + " 会员折扣：" + jsonData["memberDiscount"];
                $("#memberInfo").text(memberInfo);
                discount = jsonData["memberDiscount"];

            }
        })
    }

    var productHtml = '';
    var payTypeHtml = '';

    //算总账
    $("#cashSubmit").on("click", function (event) {

        productHtml = '';


        //获取总的产品信息
        var products = [];
        var datas = productTable.bootstrapTable('getData');
        for (var row in datas) {
            var rowData = datas[row];
            var productNum = 1;
            var product = {
                barCode: rowData["barCode"],
                discount: rowData["discount"],
                productNum: productNum,
                saleType: 1,
                salePrice: rowData["salePrice"]
            };
            products.push(product);
            var money = rowData["salePrice"];
            productHtml += '<p><span style="font-size: 10px;">' + rowData["productId"] + ' &nbsp; &nbsp;' +
                rowData["colorName"] + ' &nbsp; &nbsp;' + rowData["sizeName"] + ' &nbsp; &nbsp; &nbsp; &nbsp;' + money + '</span></p>';


        }


        //获取支付方式的信息和总价格信息
        var order = {
            "totalPrice": totalPrice,
            "totalNum": totalCount,
            "wechatPay": $("#wechatPay").val(),
            "aliPay": $("#aliPay").val(),
            "bankPay": $("#bankPay").val(),
            "cashPay": $("#cashPay").val(),
            "balancePay": $("#balancePay").val(),
            "couponPay": $("#couponPay").val(),
            "scorePay": $("#scorePay").val(),
            memberId: $("div[data-name=memberCardId]").attr("data-value"),
            orderStatus: 1,
            backChange: backChange
        };


        var cashDTO = {
            order: order,
            products: products
        };

        $.ajax({
            url: "/cash/submitCash",
            type: "POST",
            data: JSON.stringify(cashDTO),
            dataType: "json",
            contentType: "application/json",
            success: function (datas) {
                var orderId = datas["object"]["orderId"];
                console.log("finish");
                alert("收款成功!");
                printdiv(orderId);
                window.location.reload();
            }
        })


    });


    function switchMember() {

        var status = $("input[name=memberRadio]:radio:checked").val();
        if (status === "1") {
            //老用户
            $("#alreadyMemberDiv").show();
            $("#newMemberDiv").hide();
            $("#memberInfo").show();

        } else {
            //新会员
            $("#alreadyMemberDiv").hide();
            $("#newMemberDiv").show();
            $("#memberInfo").hide();
            $("div[data-name=memberCardId]").attr("data-value", 0);
        }
    }

    $("#newMemberSubmit").on("click", function (event) {
        newMember();
    });

    function newMember() {
        var member = {
            cardId: $("#memberCardId").val(),
            memberMobile: $("#memberMobile").val()
        };
        $.ajax({
            url: "/member/newMember",
            data: member,
            type: "POST",
            async: false,
            success: function (datas) {
                alert("新建成功");
                $("#radio_member").trigger('click')
                switchMember();
                $("#memberCard").val(member.memberMobile);
                reNewMemberInfo();
            }

        })
    }

    function printdiv(orderNo) {
        var LODOP = getLodop();
        LODOP.PRINT_INIT("");

        var printHtml = '<p><span style="font-size: 10px;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 德蒙斯特欢迎您&nbsp;<br/></span></p><hr/><p><span style="font-size: 10px;">店长:周影 &nbsp;位置:百花街444附20<br/></span></p>' +
            '<p><span style="font-size: 10px;">小票号:' + orderNo + ' 时间:' + new Date().toLocaleString() + '</span></p><hr/>' +
            '<p><span style="font-size: 10px;">款号 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;颜色 &nbsp; &nbsp; &nbsp; &nbsp;尺码 &nbsp; &nbsp; &nbsp; &nbsp;金额<br/></span></p>' +
            productHtml + '<hr/>' +
            '<p><span style="font-size: 10px;">总计：' + totalPrice + '元  &nbsp; 找零：' + backChange + '元<br/></span></p>' +
            payTypeHtml +
            '<hr/>' +
            '<p><span style="font-size: 10px;">会员：' + $("#memberCard").val() + ' 余额：' + memberCash + ' 积分：' + memberScores + '</span></p>' +
            '<p><span style="font-size: 10px;">添加您的专属微信客服：dmsthyzyd</span></p>' +
            '<p><span style="font-size: 10px;">德蒙斯特会员直营店再次欢迎您的光临</span></p>'
        LODOP.ADD_PRINT_HTM(3, 2, 580, 20, printHtml);
        LODOP.SET_PRINT_PAGESIZE(3, 580, 0, "0000");
        LODOP.PREVIEW();
    }


    //计算总价
    function calTotalMoney() {
        totalPrice = 0;
        totalCount = 0;
        var datas = productTable.bootstrapTable('getData');
        for (var row in datas) {
            var rowData = datas[row];
            totalCount += 1;
            totalPrice += parseInt(rowData['salePrice']);
        }

        var totalInfo = "总件数为：" + totalCount + "  总价格为：" + totalPrice;
        $("#totalPriceDiv").text(totalInfo);
        calChange();

    }


    function calChange() {
        payTypeHtml = '';
        var gotMoney = 0;
        $("input[name=getSubmit]").each(function () {
            gotMoney += parseInt($(this).val());
            var payTypeText = $("label[for='" + $(this).attr('id') + "']").text()
            if ($(this).val() !== '' && parseInt($(this).val()) !== 0) {
                payTypeHtml += '<p><span style="font-size: 10px;">' + payTypeText + '：' + parseInt($(this).val()) + '元</span></p>'
            }
        });
        backChange = gotMoney - totalPrice;
        $("#balanceButton").val("找零：" + backChange);
    }


});