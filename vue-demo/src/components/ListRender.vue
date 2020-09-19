<!--测试动态遍历和展示-->
<template>
    <div>
        <h3>遍历数组</h3>
        <ul>
            <!--Vue 2.2.0+的版本里，当在组件中使用v-for时，key是必须设置的,开发过程中可以对应主键。-->
            <!--key是给每一个vnode的唯一ID，可以依靠key，更准确、更快地拿到oldVnode中对应的vnode节点，提升虚拟DOM速度-->
            <!--如果使用index去绑定key的话，会发生莫名的BUG，特别是做增删改节点时-->
            <li v-for="(person,index) in persons " :key="person.name">
                {{index}}) {{person.name}},年龄：{{person.age}},性别：{{person.gender}}
            </li>

            <p>----------------------------------------------------</p>
            <!--persons与personsKeys数组长度相同，使用shortId作为唯一主键-->
            <li v-for="(person,index) in persons " :key="personsKeys[index]">
                id: {{personsKeys[index]}}) --------- {{person.name}},年龄：{{person.age}},性别：{{person.gender}}
            </li>
        </ul>

        <h3>遍历对象</h3>
        <ul>
            <!--item代表属性值，key代表属性变量名称-->
            <li v-for="(item,key) in persons[0] " :key="key">
                {{key}} ---------------{{item}}
            </li>
        </ul>
    </div>
</template>

<script>
    import shortId from 'shortid';
    //VM定义
    export default {
        // 声明当前组件名称
        name: 'ListRender',
        //model定义
        data() {
            return {
                persons: [
                    {name: '张三', age: 18, gender: '男'},
                    {name: '李四', age: 19, gender: '男'},
                    {name: '王五', age: 20, gender: '女'},
                    {name: '赵六', age: 21, gender: '男'},
                    {name: '李七', age: 22, gender: '男'},
                ],
                // 新建数组，专门用于存放唯一主键
                personsKeys: []
            }
        },
        //mounted钩子，组件加载完毕触发，类似方法有created
        mounted() {
            // 使用箭头函数，遍历persons.length个次数，返回短ID，组成personsKeys数组:格式['XWDDSDSS','XSSDSDSDX'....]
            this.personsKeys = this.persons.map(() => shortId.generate());
        }
    }

</script>