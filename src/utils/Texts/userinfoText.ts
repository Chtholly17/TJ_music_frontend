//定义了个人信息管理部分需要使用到的数据结构
import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";

export const baseForm = ref<FormInstance>();

export const userinfoData=reactive({
    userinfoForm:{
        user_student_number:"",
        new_nickname:'',   //昵称
        new_college:"",  //学院
        new_major:"",
        new_area1:'',
        new_area2:'',
        new_birthday:'',    //生日
        new_gender: '',   //性别
        new_signature: '',   //签名
    }
})

export const userinfoRules=reactive({
    new_nickname:[
        {
            required:true,
            trigger:"blur",
            message:"昵称不能为空"
        },
    ],
})

export const commitUserInfo=async ()=>{

    // console.log("获取用户学号2")
    // console.log( userinfoData.userinfoForm.user_student_number)
    const submitForm = unref(baseForm)
    if (!submitForm)
    {
        return
    }
    await submitForm.validate( async (valid: any) => {
        if (valid) {
            try {
                console.log("提交用户信息")
                console.log(userinfoData.userinfoForm.new_birthday)
                const response = await api.postUserInfo(userinfoData.userinfoForm); // 不能传入submitForm！
                // console.log(response.data);
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}

//获取用户信息
export const fetchUserInfo=async ()=>{

    try {
        const response = await api.getUserInfo(userinfoData.userinfoForm.user_student_number); // 不能传入submitForm！
        // userinfoData.userinfoForm.new_nickname=response.data.data.userNickname
        // userinfoData.userinfoForm.new_college=response.data.data.userCollege
        // userinfoData.userinfoForm.new_major=response.data.data.userMajor
        // userinfoData.userinfoForm.new_area1=response.data.data.userArea1
        // userinfoData.userinfoForm.new_area2=response.data.data.userArea2
        // userinfoData.userinfoForm.new_birthday=response.data.data.userBirthday
        // userinfoData.userinfoForm.new_gender=response.data.data.userGender
        // userinfoData.userinfoForm.new_signature=response.data.data.userSignature
        return response.data.data
    } catch (error: any) {
        ElMessage.error(error.code+': 获取失败，请检查网络或联系管理员')
    }
}

export const user_fetchUserImage=async (user_id:any)=>{
    const  response=await api.getUserImage({user_student_number:user_id})
    return response.data.data
}

//获取其他人的信息
export const fetchOtherInfo=async (user_id:any)=>{
    try {
        const response = await api.getUserInfo(user_id);
        return response.data.data
    } catch (error:any){
        ElMessage.error(error.code+': 获取失败，请检查网络或联系管理员')
    }

}

export const college_major=[
    {
        value:'艺术与传媒学院',
        label:'艺术与传媒学院',
        children:[
            {
                value: '广播电视学',
                label: '广播电视学'
            },
            {
                value: '广告学',
                label: '广告学'
            },
            {
                value: '表演',
                label: '表演'
            },
            {
                value: '动画',
                label: '动画'
            },
            {
                value: '广播电视编导',
                label: '广播电视编导'
            },
            {
                value: '音乐表演',
                label: '音乐表演'
            }
        ]
    },
    {
        value: '外国语学院',
        label: '外国语学院',
        children: [
            {
                value: '日语',
                label: '日语'
            },
            {
                value: '英语',
                label: '英语'
            },
            {
                value: '德语',
                label: '德语'
            }
        ]
    },
    {
        value: '人文学院',
        label: '人文学院',
        children: [
            {
                value: '汉语言文学',
                label: '汉语言文学'
            },
            {
                value: '文化产业管理',
                label: '文化产业管理'
            },
            {
                value: '哲学',
                label: '哲学'
            }
        ]
    },
    {
        value: '法学院',
        label: '法学院',
        children: [
            {
                value: '法学',
                label: '法学'
            }
        ]
    },
    {
        value: '政治与国际关系学院',
        label: '政治与国际关系学院',
        children: [
            {
                value: '政治学与行政学',
                label: '政治学与行政学'
            },
            {
                value: '社会学',
                label: '社会学'
            }
        ]
    },
    {
        value: '经济与管理学院',
        label: '经济与管理学院',
        children: [
            {
                value: '金融学',
                label: '金融学'
            },
            {
                value: '国际经济与贸易',
                label: '国际经济与贸易'
            },
            {
                value: '行政管理',
                label: '行政管理'
            },
            {
                value: '会计学',
                label: '会计学'
            },
            {
                value: '市场营销',
                label: '市场营销'
            },
            {
                value: '物流管理',
                label: '物流管理'
            },
            {
                value: '工程管理',
                label: '工程管理'
            },
            {
                value: '信息管理与信息系统',
                label: '信息管理与信息系统'
            }
        ]
    },
    {
        value: '建筑与城市规划学院',
        label: '建筑与城市规划学院',
        children: [
            {
                value: '风景园林',
                label: '风景园林'
            },
            {
                value: '城乡规划',
                label: '城乡规划'
            },
            {
                value: '历史建筑保护工程',
                label: '历史建筑保护工程'
            },
            {
                value: '建筑学',
                label: '建筑学'
            },
            {
                value: '城市设计',
                label: '城市设计'
            }
        ]
    },
    {
        value: '设计创意学院',
        label: '设计创意学院',
        children: [
            {
                value: '工业设计',
                label: '工业设计'
            },
            {
                value: '产品设计',
                label: '产品设计'
            },
            {
                value: '环境设计',
                label: '环境设计'
            },
            {
                value: '视觉传达设计',
                label: '视觉传达设计'
            }
        ]
    },
    {
        value: '土木工程学院',
        label: '土木工程学院',
        children: [
            {
                value: '港口航道与海岸工程',
                label: '港口航道与海岸工程'
            },
            {
                value: '智能建造',
                label: '智能建造'
            },
            {
                value: '土木工程',
                label: '土木工程'
            },
            {
                value: '地质工程',
                label: '地质工程'
            }
        ]
    },
    {
        value: '环境科学与工程学院',
        label: '环境科学与工程学院',
        children: [
            {
                value: '环境科学',
                label: '环境科学'
            },
            {
                value: '环境工程',
                label: '环境工程'
            },
            {
                value: '给排水科学与工程',
                label: '给排水科学与工程'
            }
        ]
    },
    {
        value: '机械与能源工程学院',
        label: '机械与能源工程学院',
        children: [
            {
                value: '建筑环境与能源应用工程',
                label: '建筑环境与能源应用工程'
            },
            {
                value: '能源与动力工程',
                label: '能源与动力工程'
            },
            {
                value: '工业工程',
                label: '工业工程'
            },
            {
                value: '机械设计制造及其自动化',
                label: '机械设计制造及其自动化'
            },
            {
                value: '智能制造工程',
                label: '智能制造工程'
            }
        ]
    },
    {
        value: '交通运输工程学院',
        label: '交通运输工程学院',
        children: [
            {
                value: '交通工程',
                label: '交通工程'
            },
            {
                value: '交通运输',
                label: '交通运输'
            }
        ]
    },
    {
        value: '汽车学院',
        label: '汽车学院',
        children: [
            {
                value: '车辆工程（汽车）',
                label: '车辆工程（汽车）'
            }
        ]
    },
    {
        value: '铁道与城市轨道交通研究院',
        label: '铁道与城市轨道交通研究院',
        children: [
            {
                value: '车辆工程',
                label: '车辆工程'
            }
        ]
    },
    {
        value: '材料科学与工程学院',
        label: '材料科学与工程学院',
        children: [
            {
                value: '新能源材料与器件',
                label: '新能源材料与器件'
            },
            {
                value: '材料科学与工程',
                label: '材料科学与工程'
            }
        ]
    },
    {
        value: '航空航天与力学学院',
        label: '航空航天与力学学院',
        children: [
            {
                value: '飞行器制造工程',
                label: '飞行器制造工程'
            },
            {
                value: '工程力学',
                label: '工程力学'
            }
        ]
    },
    {
        value: '测绘与地理信息学院',
        label: '测绘与地理信息学院',
        children: [
            {
                value: '测绘工程',
                label: '测绘工程'
            }
        ]
    },
    {
        value: '电子与信息工程学院',
        label: '电子与信息工程学院',
        children: [
            {
                value: '电气工程及其自动化',
                label: '电气工程及其自动化'
            },
            {
                value: '自动化',
                label: '自动化'
            },
            {
                value: '通信工程',
                label: '通信工程'
            },
            {
                value: '微电子科学与工程',
                label: '微电子科学与工程'
            },
            {
                value: '数据科学与大数据技术',
                label: '数据科学与大数据技术'
            },
            {
                value: '电子信息工程',
                label: '电子信息工程'
            },
            {
                value: '计算机科学与技术',
                label: '计算机科学与技术'
            },
            {
                value: '信息安全',
                label: '信息安全'
            },
            {
                value: '人工智能',
                label: '人工智能'
            }
        ]
    },
    {
         value: '软件学院',
        label: '软件学院',
        children: [
            {
                value: '软件工程',
                label: '软件工程'
            }
        ]
    },
    {
        value: '物理科学与工程学院',
        label: '物理科学与工程学院',
        children: [
            {
                value: '光电信息科学与工程',
                label: '光电信息科学与工程'
            },
            {
                value: '应用物理学',
                label: '应用物理学'
            }
        ]
    },
    {
        value: '海洋与地球科学学院',
        label: '海洋与地球科学学院',
        children: [
            {
                value: '海洋技术',
                label: '海洋技术'
            },
            {
                value: '地球物理学',
                label: '地球物理学'
            },
            {
                value: '海洋科学',
                label: '海洋科学'
            }
        ]
    },
    {
        value: '数学科学学院',
        label: '数学科学学院',
        children: [
            {
                value: '统计学',
                label: '统计学'
            },
            {
                value: '数学与应用数学',
                label: '数学与应用数学'
            }
        ]
    },
    {
        value: '化学科学与工程学院',
        label: '化学科学与工程学院',
        children: [
            {
                value: '应用化学',
                label: '应用化学'
            },
            {
                value: '化学工程与工艺',
                label: '化学工程与工艺'
            }
        ]
    },
    {
        value: '生命科学与技术学院',
        label: '生命科学与技术学院',
        children: [
            {
                value: '生物技术',
                label: '生物技术'
            },
            {
                value: '生物信息学',
                label: '生物信息学'
            }
        ]
    },
    {
        value: '口腔医学院',
        label: '口腔医学院',
        children: [
            {
                value: '口腔医学',
                label: '口腔医学'
            }
        ]
    },
    {
        value: '医学院',
        label: '医学院',
        children: [
            {
                value: '临床医学（拔尖卓越培养）',
                label: '临床医学（拔尖卓越培养）'
            },
            {
                value: '临床医学（5+3 一体化）',
                label: '临床医学（5+3 一体化）'
            },
            {
                value: '临床医学',
                label: '临床医学'
            },
            {
                value: '护理学',
                label: '护理学'
            },
            {
                value: '康复物理治疗',
                label: '康复物理治疗'
            }
        ]
    },
    {
        value: '中德工程学院',
        label: '中德工程学院',
        children: [
            {
                value: '机械电子工程',
                label: '机械电子工程'
            },
            {
                value: '汽车服务工程',
                label: '汽车服务工程'
            },
            {
                value: '建筑电气与智能化',
                label: '建筑电气与智能化'
            }
        ]
    },
    {
        value: '马克思主义学院',
        label: '马克思主义学院',
        children: [
            {
                value: '马克思主义理论',
                label: '马克思主义理论'
            }
        ]
    },
    {
        value: '国际足球学院',
        label: '国际足球学院',
        children: [
            {
                value: '运动训练',
                label: '运动训练'
            }
        ]
    }
]
