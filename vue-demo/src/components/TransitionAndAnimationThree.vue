<template>
    <div>

        <button @click="flag=!flag">切换</button>
        <p></p>
        <transition enter-active-class="animated bounceIn" leave-active-class="animated bounceOut" :duration="2000">
            <img v-if="flag" :src="pic" alt=""/>
        </transition>

    </div>
</template>

<script>
    // 如果使用@符则表示使用绝对路径
    import pic from "@/assets/logo.png";
    import animate from "animate.css";

    export default {
        name: "TransitionAndAnimationThree",
        data() {
            return {
                pic: pic,
                flag: true,
                // 此变量引用，仅仅为了不让VM报错
                animate: animate
            }
        }
    }
</script>

<style scoped>

</style>