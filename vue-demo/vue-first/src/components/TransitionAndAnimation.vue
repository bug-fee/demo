<template>
    <div>
        <button @click="show=!show">切换</button>

        <transition name="fade">
            <div class="box" v-show="show">撩课学院</div>
        </transition>

    </div>
</template>

<script>
    export default {
        name: "TransitionAndAnimation",
        data() {
            return {
                show: true
            }
        }
    }
</script>

<style scoped>
    .box {
        width: 200px;
        height: 200px;
        background-color: red;
        color: #fff;
        font-size: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .fade-enter, .fade-leave-to {
        /*透明化*/
        opacity: 0;
        /*向右滑动*/
        transform: translateX(200px);
    }

    .fade-enter-active, .fade-leave-active {
        /*两秒内以慢速完成过渡效果*/
        transition: all 2s ease-in-out;
    }
</style>