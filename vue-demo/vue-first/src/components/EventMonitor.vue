<!--测试事件绑定-->
<template>
    <div>
        <h1>{{ msg }}</h1>
        <!--添加点击事件-->
        <p><button v-on:click="alertMessage">点我</button></p>
        <!--简写-->
        <p><button @click="alertMessage">点我</button></p>
        <!--函数传入变量-->
        <p><button @click="alertMessage('王sir')">点我</button></p>
        <!--支持传入data内的属性-->
        <p><button @click="alertMessage(msg)">点我</button></p>
    </div>
</template>

<script>
    export default {
        // 声明当前组件名称
        name: 'EventMonitor',
        data() {
            return {
                msg: 'hellow vue',
            }
        },
        methods: {
            alertMessage (name) {
                alert(this.msg);
                // 注意此处使用到变量，引号为``
                this.msg = `${name}，祝你学有所成`;
            }
        }
    }
</script>
