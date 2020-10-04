<template>
    <div>
        <h3>这里是父组件</h3>
        <p>儿子给我{{ money}} </p>
        <customEventSon @hongbao="givemoney"></customEventSon>
    </div>

</template>
<script>
    import customEventSon from './customEventSon.vue';

    export default {
        name: 'customEventFather',
        components: {customEventSon},
        data() {
            return {
                money: 0,
            }
        },
        methods: {
            //自定义事件的定义(发布)
            givemoney(val) {
                this.money = val;
            }
        }

    };
</script>

<style scoped>

</style>