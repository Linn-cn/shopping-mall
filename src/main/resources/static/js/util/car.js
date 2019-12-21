/*
*@Name: 母婴商城
*@Author: xuzhiwen
*@Copyright:layui.com
*/

layui.define(['layer'], function (exports) {
    let layer = layui.layer, $ = layui.$;
    let car = {
        init: function () {
            let uls = document.getElementById('list-cont').getElementsByTagName('ul');//每一行
            let checkInputs = document.getElementsByClassName('check'); // 所有勾选框
            let checkAll = document.getElementsByClassName('check-all'); //全选框
            let SelectedPieces = document.getElementsByClassName('Selected-pieces')[0];//总件数
            let piecesTotal = document.getElementsByClassName('pieces-total')[0];//总价
            let batchdeletion = document.getElementsByClassName('batch-deletion')[0];//批量删除按钮
            let settlement = document.getElementsByClassName('Settlement')[0];//结算按钮

            //计算
            function getTotal() {
                let seleted = 0, price = 0;
                for (let i = 0; i < uls.length; i++) {
                    if (uls[i].getElementsByTagName('input')[0].checked) {
                        seleted += parseInt(uls[i].getElementsByClassName('Quantity-input')[0].value);
                        price += parseFloat(uls[i].getElementsByClassName('sum')[0].innerHTML);
                    }
                }
                SelectedPieces.innerHTML = seleted;
                piecesTotal.innerHTML = '￥' + price.toFixed(2);
            }

            function updateCount(cartId, count) {
                $.post("/shoppCart/updateCount", {cartId: cartId, commodityCount: count},
                    function (res) {
                        if (res.resultCode !== 200) {
                            layer.alert(res.message, {icon: 2});
                        }
                    });
            }

            // 小计
            function getSubTotal(ul) {
                let unitprice = parseFloat(ul.getElementsByClassName('th-su')[0].innerHTML);
                let count = parseInt(ul.getElementsByClassName('Quantity-input')[0].value);
                let SubTotal = parseFloat(unitprice * count);
                ul.getElementsByClassName('sum')[0].innerHTML = SubTotal.toFixed(2);
            }

            // 监听复选框
            for (let i = 0; i < checkInputs.length; i++) {
                checkInputs[i].onclick = function () {
                    if (this.className === 'check-all check') {
                        for (let j = 0; j < checkInputs.length; j++) {
                            checkInputs[j].checked = this.checked;
                        }
                    }
                    if (this.checked == false) {
                        for (let k = 0; k < checkAll.length; k++) {
                            checkAll[k].checked = false;
                        }
                    }
                    getTotal();
                }
            }

            // 监听删除按钮
            for (let i = 0; i < uls.length; i++) {
                uls[i].onclick = function (e) {
                    e = e || window.event;
                    let el = e.srcElement;
                    let cls = el.className;
                    let input = this.getElementsByClassName('Quantity-input')[0];
                    let val = parseInt(input.value);
                    let that = this;
                    let cartItem = this.getElementsByClassName("dele-btn")[0];
                    let cartId = cartItem.getAttribute("data-id");
                    switch (cls) {
                        case 'add layui-btn':
                            input.value = val + 1;
                            getSubTotal(this);
                            updateCount(cartId, input.value);
                            break;
                        case 'less layui-btn':
                            if (val > 1) input.value = val - 1;
                            getSubTotal(this);
                            updateCount(cartId, input.value);
                            break;
                        case 'dele-btn':
                            layer.confirm('你确定要删除吗？', {
                                yes: function (index, layero) {
                                    $.post("/shoppCart/delete", {cartId: cartId}, function (res) {
                                        if (res.resultCode !== 200) {
                                            layer.alert(res.message, {icon: 2});
                                        }
                                    });
                                    layer.close(index);
                                    that.parentNode.removeChild(that);
                                    getTotal();
                                }
                            });
                            break;
                    }
                    getTotal();
                }
            }

            // 结算按钮
            settlement.onclick = function () {
                let index = layer.load(2);
                if (SelectedPieces.innerHTML !== 0) {
                    let orderItems = [];
                    let j = 0;
                    for (let i = 0; i < uls.length; i++) {
                        let input = uls[i].getElementsByTagName('input')[0];
                        if (input.checked) {
                            let orderItem = {};
                            let amount = uls[i].getElementsByClassName('Quantity-input')[0];
                            orderItem.buyCounts = parseInt(amount.value);
                            orderItem.commodityId = input.getAttribute("data-id");
                            orderItems[j] = orderItem;
                            j++;
                        }
                    }
                    console.log(orderItems);
                    $.ajax({
                        url:"/order/addCartToOrder",
                        data: JSON.stringify(orderItems),
                        type:"post",
                        contentType:"application/json",
                        success:function (res) {
                            if (res.resultCode === 200){
                                layer.close(index);
                                location.href = "/gotoCreateOrder";
                            }
                        }
                    });
                } else {
                    layer.msg('请选择商品')
                }
            };

            batchdeletion.onclick = function () {
                if (SelectedPieces.innerHTML !== 0) {
                    layer.confirm('你确定要批量删除吗？', {
                        yes: function (index, layero) {
                            layer.close(index);
                            deleteBatch();
                            getTotal();
                        }
                    });
                } else {
                    layer.msg('请选择商品')
                }
            };

            // 批量删除
            function deleteBatch(){
                let j = 0;
                let cartIds = [];
                for (let i = 0; i < uls.length; i++) {
                    let input = uls[i].getElementsByTagName('input')[0];
                    if (input.checked) {
                        cartIds[j] = uls[i]
                            .getElementsByClassName("dele-btn")[0]
                            .getAttribute("data-id");
                        uls[i].parentNode.removeChild(uls[i]);
                        i--;
                    }
                    j++;
                }
                $.post("/shoppCart/deleteBatch", {cartIds: cartIds});
            }


            checkAll[0].checked = true;
            checkAll[0].onclick();
            for (let i = 0; i < uls.length; i++) {
                getSubTotal(uls[i]);
            }
            getTotal();
        }
    };

    exports('car', car)
}); 