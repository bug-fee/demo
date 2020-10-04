<!--测试属性绑定-->
<template>
    <!--只能有唯一一个入口div-->
    <div>
        <!--绑定href属性-->
        <a v-bind:href="site">点击进入百度</a>
        <!--绑定ID属性-->
        <div v-bind:id="'list-' + id">绑定ID</div>
        <!--简写，直接写冒号即可-->
        <!--<div :id="'list-' + id">绑定ID</div>-->
    </div>
</template>

<script>
    //VM定义
    export default {
        // 声明当前组件名称
        name: 'TestBind',
        //model定义
        data() {
            return {
                intro: 'like it it like',
                site: 'http://www.baidu.com/',
                id:1
            }
        }
    }
</script>