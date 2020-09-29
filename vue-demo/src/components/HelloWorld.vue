<template>
    <!--测试双括号表达式-->
    <div>
        <h1>{{ msg }}</h1>
        <h1 v-text="msg"></h1>
        <!--支持JS计算规则-->
        <h1>{{5+5}}</h1>
        <!--支持三目运算-->
        <h1>{{ ok ? 'YES' : 'NO' }}</h1>
        <!--支持JS函数-->
        <h1>{{ msg.split('').reverse().join('') }}</h1>
        <!--转大写-->
        <h1>{{ msg.toUpperCase() }}</h1>
        <!--指定当前标签的ID属性-->
        <div v-bind:id="'list-' + id">指定ID属性</div>
    </div>
</template>

<script>
    export default {
        // 声明当前组件名称
        name: 'HelloWorld',
        /*
         *  1.采用函数形式返回数据与采用数据冒号形式返回,区别在于函数形式可以保证该组件被复用时数据相互间可以隔离开来。
         *  2.如果采用data:形式，则组件被复用时，数据不具有隔离性。
         */
        data() {
            return {
                // msg属性支持App.vue组件被引用时，通过标签属性被传入。
                msg: 'hellow vue',
                ok: true,
                id: 1
            }
        }
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
