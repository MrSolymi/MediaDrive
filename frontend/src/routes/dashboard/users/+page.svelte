<script lang="ts">
	import type { PageData } from './$types';

	let { data }: { data: PageData } = $props();
</script>

<div class="grid w-full px-4">
	{#each Array.isArray(data.usersData) ? data.usersData : [] as userName}
		<div class="flex w-full gap-3">
			<p>{userName}</p>
		</div>
	{/each}
</div>
