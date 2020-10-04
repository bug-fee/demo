<template>
    <button @click="publishMsg">调用方法</button>
</template>

<script>
    import PubSub from 'pubsub-js';

    export default {
        name: "PubSubSonComponent",
        methods:{
            publishMsg() {
                //发布key为sendMsg的消息。
                PubSub.publish('sendMsg', "itLike ,likeIt");
            }
        }
    }
</script>