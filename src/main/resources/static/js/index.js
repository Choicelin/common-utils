$(function () {
    var searchOptions = {
        currentPage: 0,
        numPerPage: 20
    }

    var end = false
    var htmlList = '';
    function getProductByPage(searchOptions) {
        $.ajax({
            url: '/product/getProductByPage',
            data: searchOptions,
            success: function (data) {
                if (data.length) {
                    var arr = JSON.parse(data);
                    arr.forEach(function (item, idx) {
                        htmlList += '<div class="index-pro1-list"><dl><dt><a href="/product/productDetail/'+item.productId+'" ><img src="http://file.demengsite.cn/'+ item.productImag +'" /></a></dt> <dd class="ip-text"><a href="/product/productDetail/11">' + item.productName + '</a><span></span></dd> <dd class="ip-price"><strong>¥' + item.originalPrice + '</strong> <span></span></dd></dl></div>'
                    })
                    $('.index-pro1').html(htmlList);
                } else {
                    end = true;
                }
            }
        })
    }

    getProductByPage(searchOptions);

    $(window).scroll(function () {
        //已经滚动到上面的页面高度
        var scrollTop = $(this).scrollTop();
        //页面高度
        var scrollHeight = $(document).height();
        //浏览器窗口高度
        var windowHeight = $(this).height();
        //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
        if (scrollTop + windowHeight > scrollHeight) {
            if (!end) {
                searchOptions.currentPage += 1;
                getProductByPage(searchOptions);
            }
        }
    });
})

