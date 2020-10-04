<template>
    <PubSubSonComponent/>
</template>

<script>
    import PubSub from 'pubsub-js';
    import PubSubSonComponent from '@/components/PubSubSonComponent.vue';

    export default {
        name: "PubSubComponent",
        components: {PubSubSonComponent},
        //挂载完成后触发
        mounted() {
            //订阅消息（sendMsg）
            //入参message：消息key,token：消息传入的参数

            PubSub.subscribe("sendMsg", (message, token) => {
                console.log(message, token);
            });

            //订阅全部消息，使用message区分消息类别
            // PubSub.subscribeAll();

            // 取消订阅
            // PubSub.unsubscribe()

            // 取消所有订阅
            // PubSub.clearAllSubscriptions()

            // 仅订阅一次
            // PubSub.subscribeOnce()

        },

        methods:{

        }
    }
</script>