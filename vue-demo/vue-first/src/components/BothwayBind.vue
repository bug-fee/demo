<!--测试双向绑定-->
<template>
    <!--只能有唯一一个入口div-->
    <div class="hello">
        <!--绑定数据到VM的model中，model发生改变，html内容也发生改变-->
        <h1>{{ msg }}</h1>
        <!--使用v-model来实现双向绑定，input值发生改变时，同步到model中-->
        <label><input type="text" v-model="msg"/></label>
    </div>
</template>

<script>
    //VM定义
    export default {
        // 声明当前组件名称
        name: 'BothWayBind',
        //model定义
        data() {
            return {
                msg: '我们一起学VUE!!'
            }
        }
    }
</script>