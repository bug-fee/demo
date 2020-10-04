<template>
    <div id="mine">
        个人中心
        <p>-------------------</p>
        <p>姓名：{{name}}</p>
        <p>身高：{{height}}</p>
        <button @click="goBack">返回上一页</button>
    </div>
</template>

<script>
    export default {
        name: "Home",
        props: ['name','height'],
        methods: {
            goBack() {
                window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
            }
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