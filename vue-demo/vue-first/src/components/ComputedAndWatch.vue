<!--测试计算属性和监视-->
<template>
    <div>
        <p><label><input type="text" placeholder="请输入姓氏" v-model="firstName"/></label></p>
        <p><label><input type="text" placeholder="请输入名字" v-model="lastName"/></label></p>
        <p>--------------------------------------------------------------------</p>
        <!--单向：只有姓氏和名字发生变化时，input值才发生改变，反之不成立-->
        <p><label><input type="text" placeholder="请输入姓名" v-model="fullName"/></label></p>
        <p><label><input type="text" placeholder="请输入姓名" v-model="fullNameOne"/></label></p>
        <!--双向：修改姓氏、名字时发生改变，反之也支持-->
        <p><label><input type="text" placeholder="请输入姓名" v-model="fullNameTwo"/></label></p>
        <!--watch-->
        <p><label><input type="text" placeholder="请输入姓名" v-model="fullNameThree"/></label></p>
        <p>computed与watch区别：</p>
        <pre>
            1)computed能做的，watch都能做，反之则不行
            2)能用computed的地方，尽可能使用computed
            3)computed是计算一个新的属性，并将该属性挂载到vm（Vue实例）上，而watch是监听已经存在且已挂载到vm上的数据，所以用watch
               同样可以监听computed计算属性的变化（其他还有data、props）
            4)computed本质是一个惰性求值的观察者，具有缓存性，只有当依赖变化后，第一次访问computed属性，才会计算新的值，而watch则是
               当数据发生变化便会调用执行函数。
            5)从使用场景上说，computed适用一个数据被多个数据影响，而watch适用于一个数据影响多个数据。
        </pre>

    </div>
</template>

<script>
    export default {
        // 声明当前组件名称
        name: 'ComputedAndWatch',
        data() {
            return {
                // 姓
                firstName: '',
                // 名
                lastName: '',
                // 姓名，被Watch监听改变
                fullNameThree: ''
            }
        },
        // 配置计算属性
        computed: {
            // 计算属性 姓名
            fullName() {
                return this.firstName + '●' + this.lastName;
            },
            // 语法糖，使用getter方法获得计算结果
            fullNameOne: {
                get() {
                    return this.firstName + '●' + this.lastName;
                }
            },
            fullNameTwo: {
                get() {
                    console.log('调用了fullNameTwo的getter方法');
                    return this.firstName + '●' + this.lastName;
                },
                set(value) {
                    console.log(`调用了fullNameTwo的setter方法，值${value}`);
                    // 更新firstName和lastName
                    let names = value.split('●');
                    console.log(names);
                    this.firstName = names[0];
                    this.lastName = names[1];
                }
            }
        },
        // 配置监听属性
        watch: {
            // 当firstName发生改变时触发，注意事件名称必须要与属性名称相同
            firstName(value) {
                console.log(`watch监视到firstName发生改变：${value}`);
                // 更新fullNameThree
                this.fullNameThree = this.firstName + '●' + this.lastName;
            },
            // 当lastName发生改变时触发，注意事件名称必须要与属性名称相同
            lastName(value) {
                console.log(`watch监视到lastName发生改变：${value}`);
                // 更新fullNameThree
                this.fullNameThree = this.firstName + '●' + this.lastName;
            },
            // 当fullNameThree发生改变时触发，注意事件名称必须要与属性名称相同
            fullNameThree(value) {
                console.log(`watch监视到fullNameThree发生改变：${value}`);
                // 更新firstName和lastName
                let names = value.split('●');
                console.log(names);
                this.firstName = names[0];
                this.lastName = names[1];
            }
        }
    }
</script>
