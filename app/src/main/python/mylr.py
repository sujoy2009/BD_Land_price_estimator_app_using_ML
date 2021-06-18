import numpy as np
from numpy import genfromtxt
import pandas as pd
import sklearn
from sklearn import linear_model




from os.path import dirname, join
pricearray=[]
def main(regvaluee,divvaluee,cavaluee,gavaluee,ltvaluee):
    
    
   
    filename = join(dirname(__file__), 'mylrdata.csv')
    dt = pd.read_csv(filename)
   # y=dt.head()
    x=dt.drop('PRICE',axis=1).values
    m=x.shape[0]
    ones=np.ones((m,1))
    x=np.concatenate((ones,x),axis=1)
    y=dt['PRICE'].values
    lm=linear_model.LinearRegression()
    lm.fit(x,y)
    arr=lm.coef_
    pprice0=arr[0]+arr[1]*divvaluee+arr[2]*regvaluee+arr[3]*cavaluee+arr[4]*gavaluee+arr[5]*ltvaluee+arr[6]*0
    pprice0=int(pprice0)
    pprice1=arr[0]+arr[1]*divvaluee+arr[2]*regvaluee+arr[3]*cavaluee+arr[4]*gavaluee+arr[5]*ltvaluee+arr[6]*1
    pprice1=int(pprice1)
    pprice3=arr[0]+arr[1]*divvaluee+arr[2]*regvaluee+arr[3]*cavaluee+arr[4]*gavaluee+arr[5]*ltvaluee+arr[6]*3
    pprice3=int(pprice3)
    pprice5=arr[0]+arr[1]*divvaluee+arr[2]*regvaluee+arr[3]*cavaluee+arr[4]*gavaluee+arr[5]*ltvaluee+arr[6]*5
    pprice5=int(pprice5)
    pprice10=arr[0]+arr[1]*divvaluee+arr[2]*regvaluee+arr[3]*cavaluee+arr[4]*gavaluee+arr[5]*ltvaluee+arr[6]*10
    pprice10=int(pprice10)
    pricearray.append(pprice0)
    pricearray.append(pprice1)
    pricearray.append(pprice3)
    pricearray.append(pprice5)
    pricearray.append(pprice10)
    
   
    
   
    return pricearray
def ret0(flagg):
    flagg=flagg+1
    return pricearray[0]
def ret1(flagg):
    flagg=flagg+1
    return pricearray[1]
def ret3(flagg):
    flagg=flagg+1
    return pricearray[2]
def ret5(flagg):
    flagg=flagg+1
    return pricearray[3]
def ret10(flagg):
    flagg=flagg+1
    return pricearray[4]




 
