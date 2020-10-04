<template id="son">
    <div>
        <h3>这里是子组件</h3>
        <button @click="give">给父亲红包</button>
    </div>
</template>

<script>
    export default {
        name: 'customEventSon',
        data() {
            return {
                money: 3000,
            }
        },
        methods: {
            //自定义事件的触发 （ 订阅 ）
            give() {
                console.log(this.money)
                this.$emit('hongbao', this.money)
            }
        }
    };
</script>