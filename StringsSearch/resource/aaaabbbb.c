#include <stdio.h>
#include <stdlib.h>

int main()
{
	long i = 0, MAX = 10000000;
	for (i = 0; i < MAX; i++)
		printf("a");
	for (i = 0; i < MAX; i++)
		printf("b");
	for (i = 0; i < MAX; i++)
		printf("%c", (random()%2)+97);
	printf("\n");

	return 0;
}
