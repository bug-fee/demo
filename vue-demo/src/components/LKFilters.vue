<template>
    <div>
        <h3>格式化人民币</h3>
        <p>{{moneyFormat}}</p>
        <!--格式化日期-->
        <p>{{dateFormat}}</p>
    </div>
</template>

<script>
    import moment from 'moment';

    export default {
        name: "LKFilters",
        data() {
            return {
                money: 22345,
                date: new Date()
            }
        },
        computed: {
            // 格式化金额
            moneyFormat() {
                return '￥' + Number(this.money).toFixed(2);
            },
            // 格式化日期
            dateFormat() {
                return moment(this.date).format('yyyy-MM');
            }
        },
        // methods: {
        //     moneyFormat() {
        //         return '￥' + Number(this.money).toFixed(2);
        //     }
        // }
    }
</script>

<style scoped>

</style>