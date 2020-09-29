<template>
    <p>事件对象------------</p>
    <!--传递事件对象-->
    <button @click="clickBtn('小撩',$event)">点我</button>

    <p>事件修饰符----------</p>
    <!--阻止默认事件行为-->
    <a href="http://itlike.com" @click.prevent="aClick">撩课学院</a>
    <!--测试冒泡行为-->
    <div style="width: 100px;height:100px;background-color:red;" @click="divClick">
        <!--@click.stop阻止冒泡行为-->
        <button @click.stop="btnClick">点我</button>
    </div>
    <!--测试键盘按下事件-->
    <input @keyup="dealKey">

</template>

<script>
    export default {
        name: "dealEvent",
        methods: {
            // 接收事件对象
            clickBtn(args, event) {
                console.log(args);
                console.log(event);

            },
            //测试阻止默认事件行为
            aClick() {
                alert(1);
            },
            //测试冒泡行为
            divClick() {
                alert("点击了父标签");
            },
            btnClick() {
                alert("点击了里面的标签");
            },
            //测试键盘按下事件，获取按键值
            dealKey(event) {
                alert(event['keyCode']);
            }
        }
    }
</script>
