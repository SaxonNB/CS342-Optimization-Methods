//问题表示
int n=4;			//作业数
int a[MAX]={0,5,12,4,8};	//M1上的执行时间,不用下标0的元素
int b[MAX]={0,6,2,14,7};	//M2上的执行时间,不用下标0的元素
//求解结果表示
int bestf=INF;			//存放最优调度时间
int bestx[MAX];		//存放当前作业最佳调度
int total=1;			//结点个数累计

struct NodeType		//队列结点类型
{  int no;			//结点编号
   int x[MAX];			//x[i]表示第i步分配作业编号
   int y[MAX];			//y[i]=1表示编号为i的作业已经分配
   int i;			//步骤编号
   int f1;			//已经分配作业M1的执行时间
   int f2;			//已经分配作业M2的执行时间
   int lb;			//下界
   bool operator<(const NodeType &s) const	//重载<关系函数
   {
	return lb>s.lb;	//lb越小越优先出队
   }
};

void bound(NodeType &e)
{
    int sum=0;
    for(int i=1;i<=n;i++)
        if(e.y[i]==0)//当前作业未被分配
            sum+=b[i];
    e.lb=e.f2+sum;
}

void bfs()
{
    NodeType e,e1;
    priority_queue<NodeType> qu;

    memset(e.x,0,sizeof(e.x));
    messet(e.y,0,sizeof(e.y));
    e.i=0;
    e.f1=0;
    e.f2=0;
    bound(e);
    e.no=total++;
    qu.push(e);

    while(!qu.empty())
    {
        e=qu.top();
        qu.pop();
        if(e.i==n)
        {
            if(e.f2<bestf)
            {
                bestf=e.f2;
                for(int j=1;j<=n;j++)
                    bestx[j]=e.x[j];
            }
        }

        e1.i=e.i+1;
        for(int j=1;j<=n;j++)
        {
            if(e.y[j]==1)
                continue;
            for(int i=1;i<=n;i++)
                e1.x[i]=e.x[i];
            e1.x[e1.i]=j;
            for(int i=1;i<=n;i++)
                e1.y[i]=e.y[i];
            e1.y[j]=1;
            e1.f1=e.f1+a[j];
            e1.f2=max(e1.f1,e.f2)+b[j];
            bound(e1);
            if(e1.lb<bestf)
            {
                e1.no=total++;
                qu.push(e1);
            }
        }
    }
}
