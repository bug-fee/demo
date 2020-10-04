<template>
    {{name}}
</template>

<script>
    export default {
        name: "ChildComponent",
        data() {
            return {
                name: '内部组件'
            };
        },
    }

</script>

<style scoped>

</style>