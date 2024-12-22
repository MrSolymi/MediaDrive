<script lang="ts">
	import { enhance } from "$app/forms";
	import Button from "$lib/components/ui/button/button.svelte";
    import type { PageData } from "./$types";

    let { data } : { data: PageData } = $props();

    //console.log(data);
</script>

<div class="grid h-screen w-full px-4">
    {#each Array.isArray(data.invitesData) ? data.invitesData : [] as inviteToken}
        <div class="flex gap-3 w-full">
            <p>{inviteToken}</p>

            <form method="POST" use:enhance>
                <input type="hidden" name="tokenId" value={inviteToken} />
                <Button type="submit" class="w-full">
                    Delete
                </Button>
            </form>
        </div>
    {/each}
</div>