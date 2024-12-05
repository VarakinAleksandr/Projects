void PrintSqueres(int limit)
{
    int i = 1;
    while (i <= limit)
    {
        Console.Write($"{i * i} ");
        i++;
    }
}
PrintSqueres(5);
PrintSqueres(10);
PrintSqueres(15);