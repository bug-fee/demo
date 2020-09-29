<template>
    <div>
        <!--v-text和v-html-->
        <!--双括号表达式与v-text的区别在于，v-text会覆盖标签内容-->
        <p>{{content}} (Like It,It Like)</p>
        <p v-text="content"></p>
        <!--在网站上动态渲染HTML是非常危险的，因为容易导致XSS攻击-->
        <p v-html="content"></p>

        <!--v-pre-->
        <!--v-pre指令，不对指令进行预处理，不解析双括号表达式-->
        <p v-pre>{{message}}</p>

        <!--v-cloak指令，在对指令解析完成之前，不显示元素，避免出现表达式一闪而过的现象。需要配合[v-cloak]样式首先隐藏-->
        <!--只有非工程化时才会用到该指令，工程化时不会出现该问题-->
        <p v-cloak>{{message}}</p>

        <!--v-once指令，只渲染元素和组件一次。随后的重新渲染，元素/组件机器所有的子节点将被视为静态内容并跳过。这可以用于优化更新性能-->
        <p>{{name}}</p>
        <p v-once>{{name}}</p>
        <input placeholder="请输入姓名" v-model="name"/>

        <!--ref：定义引用-->
        <p ref="fish">我是一只鱼</p>
        <ChildComponent ref="child"></ChildComponent>
        <br/>
        <button @click="log">点我</button>

        <!--自定义指令-->
        <p v-upper-word="word1"></p>
        <p v-lower-word="word2"></p>
    </div>
</template>

<script>
    import ChildComponent from './ChildComponent.vue';

    export default {
        name: "OtherInstruct",
        components: {ChildComponent},
        data() {
            return {
                content: '<a href="http://www.itlike.com">撩课学院</a>',
                message: 'It like',
                name: '小撩',
                word1: 'abc',
                word2: 'ABC',
            };
        },
        methods: {
            log() {
                //获取引用
                console.log(this.$refs.fish.innerHTML);
                console.log(this.$refs.child.name);
                this.$refs.fish.innerHTML = '小撩是一条鱼';
                this.$refs.child.name = '修改内部组件';
            }

        },
        // 自定义局部指令
        directives: {
            'lower-word'(el,binding) {
                el.textContent = binding.value.toLowerCase();
            }
        }
    }
</script>

<style scoped>
    [v-cloak] {
        display: none;
    }
</style>