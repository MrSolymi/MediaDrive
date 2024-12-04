<script lang="ts">
	import LoginForm from '$lib/components/auth/LoginForm.svelte'
    import * as Card from '$lib/components/ui/card/index.js';
	import type { PageData } from '../../routes/login/$types';

	export let data: PageData;
</script>

<div class="flex h-screen w-full items-center justify-center px-4">
	<Card.Root class="mx-auto max-w-sm">
		<Card.Header>
			<Card.Title class="text-2xl">Login</Card.Title>
			<Card.Description>
				Enter your username and password below to login to your account
			</Card.Description>
		</Card.Header>
		<Card.Content>
			<div>
				<LoginForm data={data.form} />
			</div>
			<div class="mt-4 text-center text-sm">
				Don't have an account?
				<a href="/register" class="underline"> Sign up </a>
			</div>
		</Card.Content>
	</Card.Root>
</div>

