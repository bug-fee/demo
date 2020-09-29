<template>
    <!--测试搜索过滤-->
    <div>
        <div>
            <h3>排序</h3>
            <button @click="orderByAge(0)">默认</button>
            <button @click="orderByAge(2)">年龄↑</button>
            <button @click="orderByAge(1)">年龄↓</button>
        </div>

        <p>-------------------------------------------------------------------</p>

        <h3>搜索列表</h3>
        <input type="text" placeholder="请输入要搜索的姓名" v-model="searchName">
        <ul>
            <!--filterPersons：通过计算属性来获取过滤数据-->
            <li v-for="(p, index) in filterPersons" :key="personsKeys[index]">
                {{index + 1}}) 姓名：{{p.name}} --- 性别：{{p.sex}}--- 年龄：{{p.age}}--- 电话：{{p.phone}}
            </li>
        </ul>
    </div>
</template>

<script>
    import shortId from 'shortid';

    export default {
        name: "ListRenderTwo",
        data() {
            return {
                // 对搜索内容进行属性绑定
                searchName: '',
                persons: [
                    {name: '张三', sex: '女', age: 19, phone: '18921212121'},
                    {name: '李四', sex: '男', age: 29, phone: '18921221121'},
                    {name: '王五', sex: '女', age: 39, phone: '18921788721'},
                    {name: '赵六', sex: '男', age: 49, phone: '18921556121'},
                    {name: '李四', sex: '男', age: 29, phone: '18921221121'},
                    {name: '王五', sex: '女', age: 39, phone: '18921788721'},
                    {name: '李四', sex: '男', age: 29, phone: '18921221121'},
                    {name: '王五', sex: '女', age: 39, phone: '18921788721'},
                    {name: '王五', sex: '女', age: 39, phone: '18921788721'},
                    {name: '赵六', sex: '男', age: 49, phone: '18921556121'},
                    {name: '李思思', sex: '男', age: 29, phone: '18921221121'},
                    {name: '张三', sex: '女', age: 19, phone: '18921212121'},
                    {name: '李四', sex: '男', age: 29, phone: '18921221121'},
                    {name: '王五', sex: '女', age: 39, phone: '18921788721'},
                    {name: '赵六', sex: '男', age: 49, phone: '18921556121'},
                    {name: '李四', sex: '男', age: 29, phone: '18921221121'},
                    {name: '王五', sex: '女', age: 39, phone: '18921788721'},
                    {name: '李四', sex: '男', age: 29, phone: '18921221121'},
                    {name: '王五', sex: '女', age: 39, phone: '18921788721'},
                    {name: '王五', sex: '女', age: 39, phone: '18921788721'},
                    {name: '赵六', sex: '男', age: 49, phone: '18921556121'},
                    {name: '李五五', sex: '男', age: 29, phone: '18921221121'}
                ],
                personsKeys: [],
                orderType: 0
            }
        },
        // 定义计算属性函数
        computed: {
            //调用filterPersons属性时，方法体return内容默认为get方法
            filterPersons() {
                // 1. 获取数据
                //等于 this.searchName 、 this.persons 和 this.orderType，定义searchName等局部变量，仅在该代码块生效
                let {searchName, persons, orderType} = this;

                // 2. 取出数组中的数据
                let arr = [...persons];

                // 3. 过滤数组
                if (searchName.trim()) {
                    arr = persons.filter(p => p.name.indexOf(searchName) !== -1);
                }

                // 4. 排序
                if (orderType) {
                    arr.sort((p1, p2) => {
                        if (orderType === 1) { // 降序
                            return p2.age - p1.age
                        } else { // 升序
                            return p1.age - p2.age
                        }
                    });
                }
                return arr;
            }
        },
        //mounted钩子，组件加载完毕触发，类似方法有created
        mounted() {
            this.personsKeys = this.filterPersons.map(() => shortId.generate())
        },
        methods: {
            // 排序方法，只要computed方法中引用到了this的属性，属性发生变化时，自动触发computed
            orderByAge(orderType) {
                this.orderType = orderType;
            }
        }
    }
</script>

<style scoped>
    ul {
        list-style: none;
    }

    ul li {
        padding: 4px 0;
    }
</style>