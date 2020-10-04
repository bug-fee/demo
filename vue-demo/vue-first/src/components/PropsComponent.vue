<template>
    <div>姓名：{{name}}</div>
    <div>年龄：{{age}}</div>
    <p>{{person}}</p>

    <button @click="logPerson('大撩撩',60)">调用方法</button>
</template>

<script>
    export default {
        name: "PropsComponent",
        // 可以使用数组形式接收父组件传入的参数，但是不支持校验
        // props:['name','age']
        // 使用对象接受参数，并添加校验规则
        props: {
            name: String,
            age: Number,
            person: Object,
            logPerson: Function
        }
    }
</script>

<style scoped>

</style>