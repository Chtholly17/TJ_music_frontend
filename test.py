import sys

def fun(str):
    print("line1:", str)
    print("line2:", str)
    print("line3:", str)


if __name__ == "__main__":
    arg1 = sys.argv[1]  # 第一个参数
    arg2 = sys.argv[2]  # 第二个参数
    
    fun(arg1)