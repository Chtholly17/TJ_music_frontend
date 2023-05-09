import {reactive} from "vue";

interface accompanimentInfoType {
    originId: number;
    originName: string;
    originAuthor: string;
    originBgmusicFilename: string;
    originVoiceFilename: string;
    originDuration: string;
    originPrefaceFilename: string;
    originIntroduction: string;
}
interface songWorksInfoType {
    work: {
        workId: number;
        createTime: string;
        workName: string;
        workComment: string;
        workOwner: number;
        workOriginVersion: number;
        workLike: number;
        workVoiceFilename: string;
        workScore: number;
        workPrefaceFilename: string;
    };
    userNickname: string;
    userStudentNumber: string;
}
// 使用reactive设置响应式数据类型，并使用interface进行类型定义；如果为ref，则不渲染；如果不是响应式数据，则渲染迟一拍（不知道为什么…）
export const accompanimentInfoList: accompanimentInfoType[] = reactive([])
export const songWorksInfoList: songWorksInfoType[] = reactive([])
