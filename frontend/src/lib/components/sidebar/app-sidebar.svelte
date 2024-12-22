<script lang="ts">
	import * as Sidebar from '$lib/components/ui/sidebar/index.js';
	import { items } from '$lib/components/sidebar/ItemsToMenu';

	const menu = items;
</script>

<Sidebar.Root>
	<Sidebar.Header />
	<Sidebar.Content class="ml-4 mr-4">
		<Sidebar.GroupLabel class="text-4xl font-semibold pt-2 mb-5">MediaDrive</Sidebar.GroupLabel>
		<Sidebar.GroupContent>
			<Sidebar.Menu>
				{#each menu as item (item.title)}
					<Sidebar.MenuItem class="py-0.5">
						<Sidebar.MenuButton size="lg">
							{#snippet child({ props })}
								<a href={item.url} {...props}>
									<item.icon class="scale-150 mx-2.5"/>
									<span class="text-xl mb-auto mt-auto">{item.title}</span>
								</a>
							{/snippet}
						</Sidebar.MenuButton>
					</Sidebar.MenuItem>
				{/each}
			</Sidebar.Menu>
		</Sidebar.GroupContent>
	</Sidebar.Content>
	<Sidebar.Rail />
	<Sidebar.Footer />
</Sidebar.Root>
