<template>
    <div>

        <button @click="flag=!flag">切换</button>
        <p></p>
        <transition name="fade">
            <img v-if="flag" :src="pic" alt=""/>
        </transition>

    </div>
</template>

<script>
    // 如果使用@符则表示使用绝对路径
    import pic from "@/assets/logo.png";
    // import pic from "./assets/logo.png";

    export default {
        name: "TransitionAndAnimationTwo",
        data() {
            return {
                pic: pic,
                flag: true
            }
        }
    }
</script>

<style scoped>
    @keyframes fade {
        0% {
            transform: scale(0);
        }
        50% {
            transform: scale(1.5);
        }
        100% {
            transform: scale(1);
        }
    }
    .fade-enter-active {
        animation: fade 1s;
    }

    .fade-leave-active {
        animation: fade 1s reverse;
    }

</style>