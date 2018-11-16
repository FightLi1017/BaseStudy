#coding:utf-8
import os,sys

  #获取目录下所有的文件名#
res_name=os.listdir(os.getcwd()+"/src/main")
  #循环遍历文件名#
for temp in res_name:

    print(temp)

    # new_name=temp[0:6]+'.flv'
    # print new_name
    # os.rename('D:\\videos\\'+temp,'D:\\videos\\'+new_name)