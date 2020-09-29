<template>
    <div>
        <p v-if="isShow">{{str1}}</p>
        <p v-else>{{str2}}</p>
        <button @click="destroy">销毁</button>
    </div>
</template>

<script>
    export default {
        name: 'LifeCircle',
        beforeCreate: function () {
            console.group('beforeCreate 创建前状态===============》');
        },
        data() {
            return {
                isShow: false,
                str1: '撩课学院',
                str2: 'itLike , likeIt',
                intervalId: ''
            }
        },
        methods: {
            destroy() {
                // 经测试该方法在vue3中无法使用
                this.$destroy();
            }
        },
        created: function () {
            console.group('created 创建完毕状态===============》');
        },
        beforeMount: function () {
            console.group('beforeMount 挂载前状态===============》');
        },
        mounted: function () {
            console.group('mounted 挂载结束状态===============》');
            this.intervalId = setInterval(() => {
                this.isShow = !this.isShow;
            }, 1000);
        },
        beforeUpdate: function () {
            console.group('beforeUpdate 更新前状态===============》');
        },
        updated: function () {
            console.group('updated 更新完成状态===============》');
        },
        beforeUnmounted: function () {
            console.group('beforeDestroy 销毁前状态===============》');
            clearInterval(this.intervalId);
        },
        unmounted: function () {
            console.group('destroyed 销毁完成状态===============》');
        },
    }
</script>

<!--该样式只作用于当前组件，渲染HTML时避免样式污染，可以加入scoped属性-->
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
