int i = 0;
int[] arr = { 5, 4, 7, 9, 5, 8, 22, 222, 1, 1, 2 };
int max = arr[0];
while (i < arr.Length)
{
    if (arr[i] > max)
    {
        max = arr[i];
    }
    i++;
}
Console.WriteLine(max);

foreach(int e in arr)
{
    if (e>max)
    {
        max=e;
    }
}
Console.WriteLine(max);

