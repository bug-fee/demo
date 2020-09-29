<template>
    <!-- :age ，在props接收参数时校验必须传入数字，此处若带双引号则会报错，但是传入数字字面量会编译不通过，可以使用绑定方式增加冒号前缀。-->
    <PropsComponent :age=25 name="小撩妹妹" :person="p" :logPerson="logPerson"/>
</template>

<script>
    import PropsComponent from '@/components/PropsComponent.vue';

    export default {
        name: "ParentComponent",
        data() {
            return {
                p: {
                    name: '张三丰',
                    age: '600'
                }
            }
        },
        methods: {
            logPerson(name ,age) {
                // console.log(this.p);
                // 使用``大引号配合占位符可以在字符串内直接使用变量值
                console.log(`姓名：${name}, 年龄：${age}`);
            }
        },
        components: {PropsComponent},
    }
</script>

<style scoped>

</style>