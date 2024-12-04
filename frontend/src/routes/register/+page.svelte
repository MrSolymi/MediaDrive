<script lang="ts">
	import RegisterForm from '$lib/components/auth/RegisterForm.svelte';
	import * as Card from '$lib/components/ui/card/index.js';
	import type { PageData } from './$types';

	export let data:PageData
</script>

<div class="flex h-screen w-full items-center justify-center px-4">
	<Card.Root class="mx-auto max-w-sm">
		<Card.Header>
			<Card.Title class="text-2xl">Registration</Card.Title>
			<Card.Description>
				Enter your account details and inivte token below to register
			</Card.Description>
		</Card.Header>
		<Card.Content>
			<div>
				<RegisterForm data={data.form} />
			</div>
			<div class="mt-4 text-center text-sm">
				Have an account?
				<a href="/login" class="underline"> Sign In </a>
			</div>
		</Card.Content>
	</Card.Root>
</div>


