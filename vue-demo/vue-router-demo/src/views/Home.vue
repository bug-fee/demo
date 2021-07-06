<template>
    <div id="home">
        首页
        <ul>
            <li>
                <router-link to="news">新闻</router-link>
            </li>
            <li>
                <router-link to="shop">购物</router-link>
            </li>
        </ul>
        <!--添加路由出口-->
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style scoped>
    #home {
        width: 300px;
        height: 500px;
        background-color: deepskyblue;
        margin: 0px auto;
        font-size: 20px;
        color: red;
        text-align: center;
    }
</style>