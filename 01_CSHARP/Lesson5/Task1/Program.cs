int Fact(int n)
{
    if (n == 1 || n==0)
    {
        return 1;
    }
    return n * Fact(n - 1);
}
Console.WriteLine(Fact(5));