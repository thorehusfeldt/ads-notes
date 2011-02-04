public class WeightedQuickFind
{
  private int[] id, sz, next;
  private int count;

  public WeightedQuickFind(int N)
  {
    count = N;
    id = new int[N];
    next = new int[N];
    sz = new int[N];
    for (int i = 0; i < N; i++) 
    { 
      id[i] = i;
      next[i] = i;
      sz[i] = 1;
    }
  }

  public int count() { return count; }

  public boolean connected(int p, int q) { return find(p) == find (q); }
  
  public int find(int p) { return id[p]; }

  private void rename(int from, int to)
  {
    int first = next[from];
    for (int i = first; i != from; i = next[i])
	id[i] = to;
    id[from] = to;
    next[from] = next[to];
    next[to] = first;
    sz[to] += sz[from];
  }

  public void union(int p, int q) 
  {
    System.out.print(p+" "+q+" ");showinternals();
    int pID = find(p); 
    int qID = find(q);
    if (pID == qID) return;
    if (sz[pID] < sz[qID]) rename(pID,qID);
    else                   rename(qID,pID);
    count--;
  }

  void showinternals()
    {
      System.out.println(id.length+" "+count);
      for (int i = 0; i < id.length; i++) System.out.print(i+" ");
      System.out.println();
      for (int i = 0; i < id.length; i++) System.out.print(id[i]+" ");
      System.out.println();
      for (int i = 0; i < id.length; i++) System.out.print(next[i]+" ");
      System.out.println();
      for (int i = 0; i < id.length; i++) System.out.print(sz[i]+" ");
      System.out.println();


    }

  public static void main(String[] args)
    {
      int N = 10;
      WeightedQuickFind uf = new WeightedQuickFind(N);
      uf.union(4,3);
      uf.union(3,8);
      uf.union(6,5);
      uf.union(9,4);
      uf.union(2,1);
      uf.union(5,0);
      uf.union(7,2);
      uf.union(6,1);
      uf.showinternals();
    }
}