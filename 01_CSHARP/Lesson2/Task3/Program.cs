int i = 0;
int[] arr = {1,5,8,7,5,1,2,3,4,6};

while(i<arr.Length)
{
    if(arr[i]%2==0)
    {
        Console.Write($"{arr[i]} ");
    }
    i++;

}