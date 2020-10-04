<template>
    <div id="mine">
        个人中心
        <p>-------------------</p>
        <p>姓名：{{this.$route.params.name}}</p>
        <p>性别：{{this.$route.params.sex}}</p>
        <p>身高：{{$route.query.height}}</p>
    </div>
</template>

<script>
    export default {
        name: "Home",
        created() {
            // 当前路由对象
            console.log(this.$route);
            // 通过 http://127.0.0.1:8080/#/mine/张三/男?height=175 访问的页面,params属性得到动态参数，query属性得到地址栏参数。
            // /mine/张三/男
            console.log(this.$route.path);
            // /mine/张三/男?height=175
            console.log(this.$route.fullPath);
            // {name: "张三", sex: "男"}
            console.log(this.$route.params);
            // {height: "175"}
            console.log(this.$route.query);

            // 路由器对象，包含所有路由，以及路由器配置信息。
            console.log(this.$router);
        }
    }
</script>

<style scoped>
    #mine {
        width: 300px;
        height: 500px;
        background-color: deepskyblue;
        margin: 0px auto;
        font-size: 20px;
        color: orange;
        text-align: center;
    }
</style>